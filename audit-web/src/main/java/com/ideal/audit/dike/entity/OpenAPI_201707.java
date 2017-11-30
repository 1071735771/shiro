package com.ideal.audit.dike.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by chu on 2017/9/28.
 */
@Entity
@Table(name = "dop_dev_tools_201707", schema = "audit", catalog = "")
public class OpenAPI_201707 extends AutoModel {
    private String action_module;
    private String action_type;
    private String action_user;
    private Timestamp action_time;
    private String action_info;
    private String action_result;
    private String login_ip;
    private Date action_date;


    @Basic
    @Column(name = "action_module", nullable = true, length = 300)
    public String getAction_module() {
        return action_module;
    }

    public void setAction_module(String action_module) {
        this.action_module = action_module;
    }

    @Basic
    @Column(name = "action_type", nullable = true, length = 300)
    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    @Basic
    @Column(name = "action_user", nullable = true, length = 300)
    public String getAction_user() {
        return action_user;
    }

    public void setAction_user(String action_user) {
        this.action_user = action_user;
    }

    @Basic
    @Column(name = "action_result", nullable = true, length = 300)
    public String getAction_result() {
        return action_result;
    }

    public void setAction_result(String action_result) {
        this.action_result = action_result;
    }

    @Basic
    @Column(name = "login_ip", nullable = true, length = 300)
    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    @Basic
    @Column(name = "action_time", nullable = true, length = 300)
    public Timestamp getAction_time() {
        return action_time;
    }

    public void setAction_time(Timestamp action_time) {
        this.action_time = action_time;
    }

    @Basic
    @Column(name = "action_info", nullable = true, length = 300)
    public String getAction_info() {
        return action_info;
    }

    public void setAction_info(String action_info) {
        this.action_info = action_info;
    }

    @Basic
    @Column(name = "action_date", nullable = true, length = 300)
    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }
}
