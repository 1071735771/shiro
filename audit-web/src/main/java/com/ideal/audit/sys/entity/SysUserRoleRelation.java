package com.ideal.audit.sys.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jin on 2016/8/11.
 */
@Entity
@Table(name = "sys_user_role_relation", schema = "audit", catalog = "")
public class SysUserRoleRelation extends AutoModel{
    private SysUser user;
    private SysRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

}
