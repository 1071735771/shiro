package com.ideal.audit.dike.dto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by chu on 2017/9/30.
 */
public class DikeDto {
    private String action_module;
    private String action_type;
    private String action_user;
    private Timestamp action_time;
    private String action_info;
    private String action_result;
    private String login_ip;
    private Date action_date;

    public void setAction_result(String action_result) {
        this.action_result = action_result;
    }
    /*    private String action_module_b;
    private String action_type_b;
    private String user_id_b;
    private String action_user_b;
    private Timestamp action_time_b;
    private String action_info_b;
    private String open_url_b;
    private String action_result_b;
    private String login_ip_b;
    private Date action_date_b;


    private String action_module_c;
    private String action_type_c;
    private String action_user_c;
    private Timestamp action_time_c;
    private String action_info_c;
    private String action_result_c;
    private String login_ip_c;
    private Date action_date_c;*/

    public String getAction_info() {
        return action_info;
    }

    public void setAction_info(String action_info) {
        this.action_info = action_info;
    }

    public String getAction_result() {
        return action_result;
    }

   /* public void setAction_result(String action_result) {
        this.action_result = action_result;
    }

    public String getAction_module_b() {
        return action_module_b;
    }

    public void setAction_module_b(String action_module_b) {
        this.action_module_b = action_module_b;
    }

    public String getAction_type_b() {
        return action_type_b;
    }

    public void setAction_type_b(String action_type_b) {
        this.action_type_b = action_type_b;
    }

    public String getUser_id_b() {
        return user_id_b;
    }

    public void setUser_id_b(String user_id_b) {
        this.user_id_b = user_id_b;
    }

    public String getAction_user_b() {
        return action_user_b;
    }

    public void setAction_user_b(String action_user_b) {
        this.action_user_b = action_user_b;
    }

    public Timestamp getAction_time_b() {
        return action_time_b;
    }

    public void setAction_time_b(Timestamp action_time_b) {
        this.action_time_b = action_time_b;
    }

    public String getAction_info_b() {
        return action_info_b;
    }

    public void setAction_info_b(String action_info_b) {
        this.action_info_b = action_info_b;
    }

    public String getOpen_url_b() {
        return open_url_b;
    }

    public void setOpen_url_b(String open_url_b) {
        this.open_url_b = open_url_b;
    }

    public String getAction_result_b() {
        return action_result_b;
    }

    public void setAction_result_b(String action_result_b) {
        this.action_result_b = action_result_b;
    }

    public String getLogin_ip_b() {
        return login_ip_b;
    }

    public void setLogin_ip_b(String login_ip_b) {
        this.login_ip_b = login_ip_b;
    }

    public Date getAction_date_b() {
        return action_date_b;
    }

    public void setAction_date_b(Date action_date_b) {
        this.action_date_b = action_date_b;
    }

    public String getAction_module_c() {
        return action_module_c;
    }

    public void setAction_module_c(String action_module_c) {
        this.action_module_c = action_module_c;
    }

    public String getAction_type_c() {
        return action_type_c;
    }

    public void setAction_type_c(String action_type_c) {
        this.action_type_c = action_type_c;
    }

    public String getAction_user_c() {
        return action_user_c;
    }

    public void setAction_user_c(String action_user_c) {
        this.action_user_c = action_user_c;
    }

    public Timestamp getAction_time_c() {
        return action_time_c;
    }

    public void setAction_time_c(Timestamp action_time_c) {
        this.action_time_c = action_time_c;
    }

    public String getAction_info_c() {
        return action_info_c;
    }

    public void setAction_info_c(String action_info_c) {
        this.action_info_c = action_info_c;
    }

    public String getAction_result_c() {
        return action_result_c;
    }

    public void setAction_result_c(String action_result_c) {
        this.action_result_c = action_result_c;
    }

    public String getLogin_ip_c() {
        return login_ip_c;
    }

    public void setLogin_ip_c(String login_ip_c) {
        this.login_ip_c = login_ip_c;
    }

    public Date getAction_date_c() {
        return action_date_c;
    }

    public void setAction_date_c(Date action_date_c) {
        this.action_date_c = action_date_c;
    }*/

    public String getAction_module() {
        return action_module;
    }

    public void setAction_module(String action_module) {
        this.action_module = action_module;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getAction_user() {
        return action_user;
    }

    public void setAction_user(String action_user) {
        this.action_user = action_user;
    }

    public Timestamp getAction_time() {
        return action_time;
    }

    public void setAction_time(Timestamp action_time) {
        this.action_time = action_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }

}
