package com.ideal.audit.dike.dto;


import java.io.Serializable;

/**
 * Created by ybb on 2017/11/27 15:59
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_seven implements Serializable{

    private String opUser;
    private String opTime;
    private String src;

    public Sence_seven() {
    }


    public Sence_seven(String opUser, String opTime, String src) {
        this.opUser = opUser;
        this.opTime = opTime;
        this.src = src;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


}
