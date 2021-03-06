package com.ideal.audit.common.controller;

import com.alibaba.fastjson.JSON;
import com.ideal.audit.common.framework.WebMessage;
import com.ideal.audit.common.framework.WebMessageLevel;
import com.ideal.audit.common.util.BeanValidators;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
* @Description:控制器基类
* @author JRed bravecatking@gmail.com 
* @date 2015年8月24日 下午1:29:33
 */
public abstract class BaseController {
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;
	public abstract String getPortalPrefix();

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			saveError(model, StringUtils.join(list));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			saveError(redirectAttributes, StringUtils.join(list));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 添加Model消息
	 * @param messages
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}

	public void saveError(Model model, String error) {
		add(model, "global_error", new WebMessage(error, WebMessageLevel.ERROR));
	}

	public void saveMessage(Model model, String message) {
		add(model, "global_message", new WebMessage(message, WebMessageLevel.INFO));
	}

	public void saveSuccess(Model model, String message) {
		add(model, "global_success", new WebMessage(message, WebMessageLevel.SUCCESS));
	}

	protected void add(Model model, String key, WebMessage message) {
		List<WebMessage> list = new ArrayList<WebMessage>();
		if (model.containsAttribute(key)) {
			list = (List<WebMessage>) model.asMap().get(key);
		}
		list.add(message);
		if (model instanceof RedirectAttributes) {
			((RedirectAttributes) model).addFlashAttribute(key, list);
		} else {
			model.addAttribute(key, list);
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	public void writeToClient(HttpServletResponse response,String msg){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			private SimpleDateFormat datetimeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
			public void setAsText(String text) throws IllegalArgumentException {
				if (StringUtils.isNotEmpty(text)) {
					try {
						if (text.indexOf(":") == -1 && text.length() == 10) {
							setValue(this.dateFormat.parse(text));
						} else if (text.indexOf(":") > 0 && text.length() == 19) {
							setValue(this.datetimeFormat.parse(text));
						} else if (text.indexOf(":") > 0 && text.length() == 21) {
							text = text.replace(".0", "");
							setValue(this.datetimeFormat.parse(text));
						} else {
							throw new IllegalArgumentException(
									"Could not parse date, date format is error ");
						}
					} catch (ParseException ex) {
						IllegalArgumentException iae = new IllegalArgumentException(
								"Could not parse date: " + ex.getMessage());
						iae.initCause(ex);
						throw iae;
					}
				} else {
					setValue(null);
				}
			}
		});
	}

	public ModelAndView toJson(Object model, HttpServletResponse response) {
		try {
			Map map = new HashMap();
			if(model instanceof Page) {
				map.put("total", ((Page) model).getTotalElements());
				map.put("rows", ((Page) model).getContent());
			}else if(model instanceof List){
				map.put("rows",((List)model));
			}
			String node = JSON.toJSONString(map);
			response.setContentType(MediaType.APPLICATION_JSON.toString());
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(node);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Pageable asPageable(HttpServletRequest request){
		Integer pageSize = Integer.parseInt(request.getParameter("rows"));
		Integer curPage = Integer.parseInt(request.getParameter("page"))-1;
		return new PageRequest(curPage, pageSize);
	}

}
