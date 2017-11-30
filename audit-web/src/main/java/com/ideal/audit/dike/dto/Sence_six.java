package com.ideal.audit.dike.dto;

import java.io.Serializable;

/**
 * Created by ybb on 2017/11/22 14:09
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_six implements Serializable{

    private String username;
    private String optime;
    private String interfacename;
    private String result;
    private Integer count;


    public Sence_six() {
    }

    public Sence_six(String username, String optime, String interfacename, String result, Integer count) {
        this.username = username;
        this.optime = optime;
        this.interfacename = interfacename;
        this.result = result;
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getInterfacename() {
        return interfacename;
    }

    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Sence_six{" +
                "username='" + username + '\'' +
                ", optime='" + optime + '\'' +
                ", interfacename='" + interfacename + '\'' +
                ", result='" + result + '\'' +
                ", count=" + count +
                '}';
    }
}
