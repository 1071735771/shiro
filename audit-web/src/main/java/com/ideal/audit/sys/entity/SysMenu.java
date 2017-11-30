package com.ideal.audit.sys.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by jin on 2016/8/11.
 */
@Entity
@Table(name = "sys_menu", schema = "audit", catalog = "")
public class SysMenu extends AutoModel {
    private Long parentId;
    private String parentIds;
    private String menuName;
    private Integer sortNumber;
    private String href;
    private String target;
    private String menuIcon;
    private String isShow;
    private String permission;
    private Long createUser;
    private Date createDate;
    private Long modifyUser;
    private Date modifyDate;
    private String remark;
    private String isDelete;

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "parent_ids", nullable = true, length = 300)
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Basic
    @Column(name = "menu_name", nullable = false, length = 300)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "sort_number", nullable = true)
    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    @Basic
    @Column(name = "href", nullable = true, length = 500)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "target", nullable = true, length = 15)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "menu_icon", nullable = true, length = 100)
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Basic
    @Column(name = "is_show", nullable = false, length = 1)
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "permission", nullable = true, length = 200)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "create_user", nullable = false)
    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "modify_user", nullable = true)
    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    @Basic
    @Column(name = "modify_date", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
    @Column(name = "is_delete", nullable = true, length = 1)
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 菜单列表排序
     * @param list 目的list
     * @param sourcelist 原始list
     * @param parentId 父ID
     * @param cascade 是否级联下级菜单
     */
    public static void sortList(List<SysMenu> list, List<SysMenu> sourcelist, Long parentId, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            SysMenu e = sourcelist.get(i);
            if (e.getParentId()!=null
                    && e.getParentId().equals(parentId)){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        SysMenu child = sourcelist.get(j);
                        if (child.getParentId()!=null&&child.getParentId().equals(e.getId())){
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
