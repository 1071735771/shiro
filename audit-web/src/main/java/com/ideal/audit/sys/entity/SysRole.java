package com.ideal.audit.sys.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jin on 2016/8/11.
 */
@Entity
@Table(name = "sys_role", schema = "audit", catalog = "")
public class SysRole extends AutoModel{
    private String roleName;
    private String remark;
    private Integer isShow;
    private Date createDate;
    private Date modifyDate;
    private Set<SysRoleMenuRelation> roleMenus = new HashSet<SysRoleMenuRelation>(0);

    @Basic
    @Column(name = "role_name", nullable = false, length = 200)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 300)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "is_show", nullable = true)
    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "modify_date", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "role")
    @OrderBy(value = "id ASC")
    public Set<SysRoleMenuRelation> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<SysRoleMenuRelation> roleMenus) {
        this.roleMenus = roleMenus;
    }

}
