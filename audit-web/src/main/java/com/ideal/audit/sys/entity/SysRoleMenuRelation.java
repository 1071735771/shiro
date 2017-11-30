package com.ideal.audit.sys.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jin on 2016/8/11.
 */
@Entity
@Table(name = "sys_role_menu_relation", schema = "audit", catalog = "")
public class SysRoleMenuRelation {
    private int id;
    private SysRole role;
    private SysMenu menu;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    public SysMenu getMenu() {
        return menu;
    }

    public void setMenu(SysMenu menu) {
        this.menu = menu;
    }

}
