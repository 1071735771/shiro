package com.ideal.audit.warning.controller;

import com.ideal.audit.framework.DataGridDto;
import com.ideal.audit.warning.entity.Send;
import com.ideal.audit.warning.entity.log;
import com.ideal.audit.warning.form.search.SendSearchForm;
import com.ideal.audit.warning.service.LogWarningService;
import com.ideal.audit.warning.service.SendService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chu on 2017/9/20.
 */
@Controller
@RequestMapping("/send")
public class SendController {
    private static Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private SendService sendService;

    @Resource
    private LogWarningService logWarningService;

    @RequiresPermissions("send:index")
    @RequestMapping(value="index")
    public void index(Model model){
        model.addAttribute("searchForm",null);
    }


    @RequiresPermissions("send:pub")
    @RequestMapping(value="send_pub",method = RequestMethod.GET)
    @ResponseBody
    /*public Model index(Model model){
        List<LogWarning> page = logWarningService.getInstance();
        System.out.println(page+"))))))))))))))))))))))))))))))))))))0");
        model.addAttribute("searchForm",new DataGridDto<LogWarning>(page));
        return model;
    }*/
    public Map getInstance(Integer rows, Integer page, HttpServletRequest request){
        System.out.println("----------------进入---------------------");
        Integer pageSize = rows == null ? 10 : rows;
        Integer curPage = page == null ? 0 : page - 1;
        Pageable pager = new PageRequest(curPage, pageSize);
        log log = new log();
        Page<log> pages = logWarningService.getInfo(log,pager);
        Map data = new HashMap();
        data.put("total", pages.getTotalElements());
        data.put("rows", pages.getContent());
        return data;
    }

    @RequiresPermissions("send:list")
    @RequestMapping(value="send_list",method = RequestMethod.POST)
    @ResponseBody
    public DataGridDto<Send> getKafkaList(SendSearchForm searchForm){
        Page<Send> page= sendService.getPageBySearchForm(searchForm);
        return new DataGridDto<Send>(page);
    }


    @RequiresPermissions("send:edit")
    @RequestMapping(value="edit")
    public void index(Long id ,Model model){
        Send send=new Send();
        if(id !=null){
            send = sendService.getById(id);
            model.addAttribute("command", send);
        }
        model.addAttribute("command",send);
    }

    @RequiresPermissions("send:save")
    @RequestMapping(value="save")
    public String save(Send send , RedirectAttributes redirectAttr){
        try{
            sendService.saveUser(send);
            if(send.getId()!=null){
                redirectAttr.addFlashAttribute("message", "修改成功!");
            }else{
                redirectAttr.addFlashAttribute("message", "添加成功!");
            }
            return "redirect:edit";
        }catch(Exception e){
            e.printStackTrace();
            if(send.getId()!=null){
                redirectAttr.addFlashAttribute("message", "修改成功!");
            }else{
                redirectAttr.addFlashAttribute("message", "添加成功!");
            }
            return "redirect:edit";
        }
    }


    @RequiresPermissions("send:delete")
    @RequestMapping(value="delete")
    @ResponseBody
    public Integer userDel(@RequestParam String idStr, RedirectAttributes redirectAttr){
        Integer result = 0;
        try{
            String[] ids = idStr.split(",");
            sendService.delByIds(ids);
        }catch(Exception e){
            logger.error("删除用户失败",e);
            result = 1;
        }
        return result;
    }

}
