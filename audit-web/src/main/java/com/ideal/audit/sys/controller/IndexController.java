package com.ideal.audit.sys.controller;

import com.google.common.collect.Lists;
import com.ideal.audit.sys.entity.SysMenu;
import com.ideal.audit.sys.service.MenuService;
import com.ideal.audit.sys.service.UserService;
import com.ideal.audit.sys.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 * Created by snow on 2016/6/14.
 */
@Controller
@RequestMapping(SysBaseController.PORTAL_PREFIX)
public class IndexController extends SysBaseController{

    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;

    @RequestMapping({"","index"})
    public void index(Model model){
        List<SysMenu> menuList = null;
        //id为1的用户是超级管理员，用户所有的权限
        if(UserUtils.getCurrentUser().getId() == 1){
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("EQ_isDelete",0);
            params.put("EQ_isShow",0);
            menuList = menuService.findAllMenuByParams(params);
        }else{
            menuList = Lists.newArrayList();
            //查询当前用户的所有菜单
            List<SysMenu> menus = userService.getUserSysmenusByUserId(UserUtils.getCurrentUser().getId());
            if(menus!=null&&menus.size()>0){
                for(SysMenu menu:menus){
                    if("0".equals(menu.getIsShow()) && "0".equals(menu.getIsDelete())){
                        menuList.add(menu);
                    }
                }
            }
        }
        model.addAttribute("menuList", menuList);
        model.addAttribute("loginUser", UserUtils.getCurrentUser());
    }



}
