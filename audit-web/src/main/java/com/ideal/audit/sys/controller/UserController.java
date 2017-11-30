package com.ideal.audit.sys.controller;

import com.ideal.audit.common.framework.JsonObject;
import com.ideal.audit.framework.DataGridDto;
import com.ideal.audit.sys.entity.SysRole;
import com.ideal.audit.sys.form.edit.UserEditForm;
import com.ideal.audit.sys.entity.SysUser;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import com.ideal.audit.sys.form.search.UserSearchForm;
import com.ideal.audit.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController 系统用户模块控制器
 * JRed(breavecatking@gmail.com)
 * 2016/6/23 15:23
 **/
@Controller
@RequestMapping(SysBaseController.PORTAL_PREFIX+"/user/")
public class UserController  extends SysBaseController{

    @Resource
    private UserService userService;

    @RequiresPermissions("sys:user:index")
    @RequestMapping(value="index")
    public void index(Model model){
        model.addAttribute("searchForm",new UserSearchForm());
    }

    @RequiresPermissions("sys:user:index")
    @RequestMapping(value="user_list",method = RequestMethod.POST)
    @ResponseBody
    public DataGridDto<SysUser> getUserList(UserSearchForm searchForm){
        return new DataGridDto<SysUser>(userService.getPageBySearchForm(searchForm));
    }

    /**
     *
     * @Title: userEdit
     * @Description: 用户添加页面
     * @author JRed bravecatking@gmail.com
     * @param @param id
     * @param @param model
     * @param @return
     * @return String
     * @throws
     */
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value="edit")
    public void userEdit(Long id,Model model){
        UserEditForm user = new UserEditForm();
        if(id !=null){
            user = new UserEditForm(userService.getById(id));
            String[] sels = getSelectedRoleIds(userService.getUserRoleRelationByUserId(user.getId()));
            model.addAttribute("selRoleIds", sels[0]);
            model.addAttribute("selRoleNames", sels[1]);
        }
        model.addAttribute("command",user);
    }

    private String[] getSelectedRoleIds(List<SysUserRoleRelation> list){
        String ids = "";
        String names = "";
        if(list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                SysRole role = list.get(i).getRole();
                if(i<list.size()-1){
                    ids += role.getId()+",";
                    names += role.getRoleName()+",";
                }else{
                    ids += role.getId();
                    names += role.getRoleName();
                }
            }
        }
        return new String[]{ids,names};
    }




    @RequiresPermissions("sys:user:save")
    @RequestMapping(value="save")
    @ResponseBody
    public JsonObject userSave(SysUser user, RedirectAttributes redirectAttr){
        try{
            userService.saveUser(user);
            return JsonObject.refresh("保存用户["+user.getUserName()+"]成功!");
        }catch(Exception e){
            e.printStackTrace();
            return JsonObject.error("保存用户["+user.getUserName()+"]失败!");
        }
    }


    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value="delete")
    public String userDel(String[] ids,RedirectAttributes redirectAttr){
        try{
            userService.delUserByIds(ids);
            redirectAttr.addFlashAttribute("message", "删除成功!");
        }catch(Exception e){
            redirectAttr.addFlashAttribute("message", "删除失败!");
        }
        return "redirect:index";
    }



}
