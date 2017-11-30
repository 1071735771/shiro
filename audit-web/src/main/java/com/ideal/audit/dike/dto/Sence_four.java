package com.ideal.audit.dike.dto;

import java.io.Serializable;

/**
 * Created by ybb on 2017/11/27 15:59
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_four implements Serializable{

    private String opUser;
    private String opTime;
    private String sysName;
    private String moduleCode;
    private String buttonCode;
    private String opType;
    private String opResult;

    public Sence_four() {
    }

    public Sence_four(String opUser, String opTime, String sysName, String moduleCode, String buttonCode, String opType, String opResult) {
        this.opUser = opUser;
        this.opTime = opTime;
        this.sysName = sysName;
        this.moduleCode = moduleCode;
        this.buttonCode = buttonCode;
        this.opType = opType;
        this.opResult = opResult;
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

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }
}
