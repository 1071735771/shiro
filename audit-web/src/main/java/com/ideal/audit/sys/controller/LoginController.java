package com.ideal.audit.sys.controller;

import com.ideal.audit.sys.shiro.SysAuthenticationFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Wayne
 * Time: 2016/6/15  10:30
 * Email: wangyiwei@join-cn.com
 */
@Controller
@RequestMapping("/")
public class LoginController extends SysBaseController {
    /**
     * 跳转至登陆页面
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login() {
        return "/sys/login";
    }

    /**
     * 登陆失败处理方法
     * @param username
     * @param request
     * @param redirect
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
                        HttpServletRequest request, RedirectAttributes redirect) {
        Object errorName = request
                .getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String)request.getAttribute(SysAuthenticationFilter.MESSAGE_PARAM);
        if (errorName != null) {
            redirect.addFlashAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME,
                    errorName);
        }
        redirect.addFlashAttribute("username", username);
        redirect.addFlashAttribute(SysAuthenticationFilter.MESSAGE_PARAM, message);
        return "redirect:login";
    }

    /**
     * 执行登出
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }
}
