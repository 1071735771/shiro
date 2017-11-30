package com.ideal.audit.dike.controller;

import com.ideal.audit.dike.dto.*;
import com.ideal.audit.dike.service.DikeService;
import com.ideal.audit.framework.DataGridDto;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chu on 2017/9/28.
 */
@Controller
@RequestMapping("/dike")
public class DikeController {

    @Resource
    private DikeService dikeService;

    @RequestMapping(value="index")
    public void index(){
    }
    @RequestMapping(value="index_2")
    public void index_2(){
    }
    @RequestMapping(value="index_3")
    public void index_3(){
    }
    @RequestMapping(value="index_4")
    public void index_4(){
    }
    @RequestMapping(value="index_5")
    public void index_5(){
    }
    @RequestMapping(value="index_6")
    public void index_6(){
    }
    @RequestMapping(value="index_7")
    public void index_7(){
    }
    @RequestMapping(value="text")
    public void text(){
    }


//    @RequiresPermissions("dike:list")
//    @RequestMapping(value="dike_list",method = RequestMethod.POST)
//    @ResponseBody
//    public Map list(Integer rows, Integer page, HttpServletRequest request){
//        String loginIP=request.getParameter("loginIP");
//        String actionUser=request.getParameter("actionUser");
//        String startTime=request.getParameter("startTime");
//        String endTime=request.getParameter("endTime");
//        String type=request.getParameter("type");
//        Integer pageSize = rows == null ? 10 : rows;
//        Integer curPage = page == null ? 0 : page - 1;
//        Pageable pager = new PageRequest(curPage, pageSize);
//        DikeDto dto = new  DikeDto();
//        Page<DikeDto> pages = dikeService.getData(dto,pager,loginIP,actionUser,startTime,endTime,type);
//        Map data = new HashMap();
//        data.put("total", pages.getTotalElements());
//        data.put("rows", pages.getContent());
//        return data;
//    }

    @RequiresPermissions("dike:list")
    @RequestMapping(value="dike_list",method = RequestMethod.POST)
    @ResponseBody
    public Map pageList(Integer rows, Integer page, HttpServletRequest request){

        String grant = request.getParameter("grant");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        //1
        String userName=request.getParameter("userName");
        String type=request.getParameter("type");
        //2
        String portalsUser = request.getParameter("portalsUser");
        String Hname = request.getParameter("Hname");
        //3
        String webusername = request.getParameter("webusername");
        String noticeId = request.getParameter("noticeId");
        //6
        String username = request.getParameter("username");
        String interfacename = request.getParameter("interfacename");
        // 4、7
        String opUser = request.getParameter("opUser");
        //4
        String sysName = request.getParameter("sysName");
        //5
        String webName = request.getParameter("webName");
        String sysCodeAndName = request.getParameter("sysCodeAndName");
        String terminalIP = request.getParameter("terminalIP");
        //7
        String src = request.getParameter("src");

        Integer pageSize = rows == null ? 10 : rows;
        Integer curPage = page == null ? 0 : page - 1;
        Pageable pager = new PageRequest(curPage, pageSize);
        Map data = new HashMap();
        if (grant == "1" || grant.equals("1")){
            Sence_one so = new  Sence_one();
            Page<Sence_one> pages = dikeService.getResourceAsOne(so,pager,type,userName,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "2" || grant.equals("2")){
            Sence_two so = new Sence_two();
            Page<Sence_two> pages = dikeService.getResourceAsTwo(so,pager,portalsUser,Hname,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "3" || grant.equals("3")){
            Sence_three so = new Sence_three();
            Page<Sence_three> pages = dikeService.getResourceAsThree(so,pager,webusername,noticeId,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "4" || grant.equals("4")){
            Sence_four so = new Sence_four();
            Page<Sence_four> pages = dikeService.getResourceAsFour(so,pager,opUser,sysName,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "5" || grant.equals("5")){
            Sence_five so = new Sence_five();
            Page<Sence_five> pages = dikeService.getResourceAsFive(so,pager,webName,sysCodeAndName,terminalIP,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "6" || grant.equals("6")){
            Sence_six so = new Sence_six();
            Page<Sence_six> pages = dikeService.getResourceAsSix(so,pager,username,interfacename,startTime,endTime);
            data = setValue(pages);
        }else if (grant == "7" || grant.equals("7")){
            Sence_seven so = new Sence_seven();
            Page<Sence_seven> pages = dikeService.getResourceAsSeven(so,pager,opUser,src,startTime,endTime);
            data = setValue(pages);
        }
      return  data;
    }



    public static Map setValue(Page<?> pages ){
        Map data = new HashMap();
        data.put("total", pages.getTotalElements());
        data.put("rows", pages.getContent());
        return data;
    }

    /**
     * @Author:  ybb
     * @Description :
     * @Date:   2017/11/28  13:56
     */
    @RequiresPermissions("dike:other")
    @RequestMapping(value="dike_other",method = RequestMethod.POST)
    @ResponseBody
    public Map otherList(Integer rows, Integer page, HttpServletRequest request){
        Map map = new HashMap();
        String optype = request.getParameter("optype");
        String username = request.getParameter("username");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Integer pageSize = rows == null ? 10 : rows;
        Integer curPage = page == null ? 0 : page - 1;
        Pageable pager = new PageRequest(curPage, pageSize);
        TextCollection tc = new TextCollection();
        Page<TextCollection> pages = dikeService.getResourceAsText(tc,pager,optype,username,startTime,endTime);
        map = setValue(pages);
        return map;
    }

    }
