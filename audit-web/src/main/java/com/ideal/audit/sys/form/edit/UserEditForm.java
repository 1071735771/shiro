package com.ideal.audit.sys.form.edit;

import com.ideal.audit.common.util.BeanUtils;
import com.ideal.audit.sys.entity.SysUser;

/**
 * Created by jin on 2016/8/17.
 */
public class UserEditForm extends SysUser {
    private String rePassword;
    private String[] roleIds;

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public UserEditForm(){}

    public UserEditForm(SysUser user){
        BeanUtils.copyProperties(user,this);
        this.setPassword("");
    }
}
