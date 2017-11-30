package com.ideal.audit.sys.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ideal.audit.common.framework.JsonObject;
import com.ideal.audit.framework.DataGridDto;
import com.ideal.audit.sys.entity.SysMenu;
import com.ideal.audit.sys.entity.SysRole;
import com.ideal.audit.sys.entity.SysUser;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import com.ideal.audit.sys.form.edit.UserEditForm;
import com.ideal.audit.sys.form.search.UserSearchForm;
import com.ideal.audit.sys.service.MenuService;
import com.ideal.audit.sys.service.UserService;
import com.ideal.audit.sys.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController 系统用户模块控制器
 * JRed(breavecatking@gmail.com)
 * 2016/6/23 15:23
 **/
@Controller
@RequestMapping(SysBaseController.PORTAL_PREFIX+"/menu/")
public class MenuController extends SysBaseController{

    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;

    @RequiresPermissions("sys:menu:index")
    @RequestMapping(value="index")
    public void index(Model model){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("EQ_isDelete",0);
        List<SysMenu> sourceList = menuService.findAllMenuByParams(params);
        List<SysMenu> dstList = Lists.newArrayList();
        SysMenu.sortList(dstList,sourceList, (long) 0,true);
        model.addAttribute("menuList",dstList);
    }

    /**
     * 获取上级菜单名称
     * @param extId
     * @param isShowHide
     * @param response
     * @return
     */
    @RequestMapping(value = "ajax_menu_tree")
    @ResponseBody
    public List ajaxMenuTree(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
        List<Object> mapList = Lists.newArrayList();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("EQ_isDelete",0);
        List<SysMenu> list = menuService.findAllMenuByParams(params);
        for (int i=0; i<list.size(); i++){
            SysMenu m = list.get(i);
            if (org.apache.commons.lang3.StringUtils.isBlank(extId) || (extId!=null && !extId.equals(m.getId()))){
                if(isShowHide != null && isShowHide.equals("0") && "0".equals(m.getIsShow())){
                    continue;
                }
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", m.getId());
                map.put("pId", m.getParentId());
                map.put("name",m.getMenuName());
                mapList.add(map);
            }
        }
        return mapList ;
    }




    /**
     *
     * @Title: ajaxPowerMenuTree
     * @Description: 异步获取所有可用的权限菜单
     * @author JRed bravecatking@gmail.com
     * @param @param extId
     * @param @param response
     * @return void
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "ajax_power_menutree")
    public List ajaxPowerMenuTree(HttpServletResponse response) {
        List<Object> mapList = Lists.newArrayList();
        List<SysMenu> list = new ArrayList();
        SysUser user = UserUtils.getCurrentUser();
        //如果是超级管理员
        if(user.getId()==1){
            //获取所有可用的权限菜单
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("EQ_isDelete",0);
            list = menuService.findAllMenuByParams(params);
        }else{
            //获取当前登录用户可配置的权限
            List<SysMenu> menus = userService.getUserSysmenusByUserId(user.getId());
            if(menus!=null&&menus.size()>0){
                for(SysMenu menu:menus){
                    if("0".equals(menu.getIsDelete())){
                        list.add(menu);
                    }
                }
            }
        }
        Map<String, Object> root = Maps.newHashMap();
        root.put("id", 0);
        root.put("pId", 0);
        root.put("name", "权限菜单");
        mapList.add(root);
        for (int i=0; i<list.size(); i++){
            SysMenu m = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", m.getId());
            map.put("pId", m.getParentId());
            map.put("name",m.getMenuName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     *
     * @Title: menuAdd
     * @Description: 菜单编辑页面
     * @author JRed bravecatking@gmail.com
     * @param @return
     * @return String
     * @throws
     */
    @RequiresPermissions(value="sys:menu:edit")
    @RequestMapping(value="add")
    public void menuEdit(@RequestParam(required=false) String id,Model model){
        SysMenu menu = new SysMenu();
        if(!StringUtils.isEmpty(id)){
            menu  = menuService.findOneSysMenu(Long.parseLong(id));
        }
        model.addAttribute("menu", menu);
        //父级菜单
        if(menu.getParentId()!=null&&menu.getParentId()!=0){
            model.addAttribute("parentMenu",menuService.findOneSysMenu(menu.getParentId()));
        }
    }

    /**
     * 保存菜单信息
     * @param menu
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions(value="sys:menu:save")
    @RequestMapping("menu_save")
    public String menuSave(SysMenu menu,Model model,RedirectAttributes redirectAttributes){
        long menuPkId = menu.getId();
        SysUser currentUser = UserUtils.getCurrentUser();
        if (!beanValidator(model, menu)){
            model.addAttribute("message", "必填项不可为空！");
            if(menuPkId  == 0){
                if(null != menu.getParentId()){
                    return "redirect:add?model"+model+"&parentId="+menu.getParentId();
                }else{
                    return "redirect:add?model"+model;
                }
            }
        }
        menuService.saveOrUpdateSysmenu(menu);
        addMessage(redirectAttributes, "保存菜单'" + menu.getMenuName() + "'成功");
        return "redirect:index";
    }

    /**
     *
     * @Title: delete
     * @Description: 非物理删除，只是将字段del_flag设置1
     * @author JRed bravecatking@gmail.com
     * @param @param id
     * @param @param redirectAttributes
     * @param @return
     * @return String
     * @throws
     */
    @RequiresPermissions(value="sys:menu:delete")
    @RequestMapping("/menu_delete")
    public String menuDelete(@RequestParam(required=true) String id,RedirectAttributes redirectAttributes ){
        menuService.deleteCascade(Long.valueOf(id),Long.valueOf(id));
        addMessage(redirectAttributes, "删除菜单成功");
        return "redirect:index";
    }
}
