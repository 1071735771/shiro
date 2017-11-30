package com.ideal.audit.warning.controller;

import com.ideal.audit.common.framework.JsonObject;
import com.ideal.audit.framework.DataGridDto;
import com.ideal.audit.sys.controller.SysBaseController;
import com.ideal.audit.warning.entity.Rule;
import com.ideal.audit.warning.form.search.SendSearchForm;
import com.ideal.audit.warning.service.RuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chu on 2017/9/28.
 */
@Controller
@RequestMapping("/rule")
public class RuleController extends SysBaseController {
    private static Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private RuleService ruleService;


    @RequiresPermissions("rule:index")
    @RequestMapping(value="index")
    public void index(Model model){
        model.addAttribute("searchForm",null);
    }


    @RequiresPermissions("rule:list")
    @RequestMapping(value="rule_list",method = RequestMethod.POST)
    @ResponseBody
    public Map getKafkaList(Integer rows, Integer page, HttpServletRequest request){
        Integer pageSize = rows == null ? 10 : rows;
        Integer curPage = page == null ? 0 : page - 1;
        Map<String, Object> searchParams = new HashMap<String, Object>();
        Pageable pager = new PageRequest(curPage, pageSize);
        Page<Rule> pages= ruleService.searchRulePage(searchParams,pager);
        Map data = new HashMap();
        data.put("total", pages.getTotalElements());
        data.put("rows", pages.getContent());
        return data;
    }

    @RequiresPermissions("rule:edit")
    @RequestMapping(value="edit")
    public void index(Long id ,Model model){
        Rule rule=new Rule();
        if(id !=null){
            rule = ruleService.getById(id);
            model.addAttribute("command", rule);
        }
        model.addAttribute("command",rule);
    }

    @RequiresPermissions("rule:save")
    @RequestMapping(value="save")
    @ResponseBody
    public JsonObject save(Rule rule , RedirectAttributes redirectAttr){
        try{
            ruleService.saveRule(rule);
            if(rule.getId()!=null){
                return JsonObject.refresh("修改成功!");
            }else{
                return JsonObject.refresh("添加成功!");
            }
        }catch(Exception e){
            e.printStackTrace();
            if(rule.getId()!=null){
                return JsonObject.refresh("修改失败!");
            }else{
                return JsonObject.refresh("添加失败!");
            }
        }
    }

    @RequiresPermissions("send:delete")
    @RequestMapping(value="delete")
    @ResponseBody
    public Integer userDel(@RequestParam String idStr, RedirectAttributes redirectAttr){
        Integer result = 0;
        try{
            String[] ids = idStr.split(",");
            ruleService.delByIds(ids);
        }catch(Exception e){
            logger.error("删除用户失败",e);
            result = 1;
        }
        return result;
    }

}
