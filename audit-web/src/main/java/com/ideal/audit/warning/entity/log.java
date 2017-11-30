package com.ideal.audit.warning.entity;

/**
 * Created by ybb on 2017/10/12.
 */
public class log {

    private String actionModule;//操作模块'
    private String actionType;//'操作类型',
    private String userId;// '用户ID',
    private String actionUser;// '操作人名称',
    private String actionTime;//'操作时间',
    private String actionInfo;//'操作信息',
    private String openUrl;// '操作URL',
    private String actionResult;// '操作结果',
    private String loginIp;//'登录ip',
    private String actionDate;//'操作日期'

    public log() {
    }

    public String getActionModule() {
        return actionModule;
    }

    public void setActionModule(String actionModule) {
        this.actionModule = actionModule;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionUser() {
        return actionUser;
    }

    public void setActionUser(String actionUser) {
        this.actionUser = actionUser;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }
}
