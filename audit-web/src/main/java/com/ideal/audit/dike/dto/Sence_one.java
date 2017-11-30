package com.ideal.audit.dike.dto;

import java.io.Serializable;

/**
 * Created by ybb on 2017/11/22 14:12
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_one implements Serializable{

    private String userName;
    private String opTime;
    private String cmd;
    private int count;


    public Sence_one() {
    }

    public Sence_one(String cmd, String userName, String opTime, int count) {
        this.cmd = cmd;
        this.userName = userName;
        this.opTime = opTime;
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Sence_one{" +
                "userName='" + userName + '\'' +
                ", opTime='" + opTime + '\'' +
                ", cmd='" + cmd + '\'' +
                ", count=" + count +
                '}';
    }
}
