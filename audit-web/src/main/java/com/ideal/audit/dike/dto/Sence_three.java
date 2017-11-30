package com.ideal.audit.dike.dto;

/**
 * Created by ybb on 2017/11/22 14:16
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_three {

    private String webusername;
    private String username;
    private String appID;
    private String comm;
    private String dateTime;
    private String driverType;
    private String endTime;
    private String flag;
    private String host;
    private String module;
    private String noticeDescription;
    private String noticeId;
    private String noticeType;
    private String system;
    private String time;
    private String workerId;

    public Sence_three() {
    }

    public Sence_three(String webusername, String username, String appID, String comm, String dateTime, String driverType, String endTime, String flag, String host, String module, String noticeDescription, String noticeId, String noticeType, String system, String time, String workerId) {
        this.webusername = webusername;
        this.username = username;
        this.appID = appID;
        this.comm = comm;
        this.dateTime = dateTime;
        this.driverType = driverType;
        this.endTime = endTime;
        this.flag = flag;
        this.host = host;
        this.module = module;
        this.noticeDescription = noticeDescription;
        this.noticeId = noticeId;
        this.noticeType = noticeType;
        this.system = system;
        this.time = time;
        this.workerId = workerId;
    }

    public String getWebusername() {
        return webusername;
    }

    public void setWebusername(String webusername) {
        this.webusername = webusername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getNoticeDescription() {
        return noticeDescription;
    }

    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    @Override
    public String toString() {
        return "Sence_three{" +
                "webusername='" + webusername + '\'' +
                ", username='" + username + '\'' +
                ", appID='" + appID + '\'' +
                ", comm='" + comm + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", driverType='" + driverType + '\'' +
                ", endTime='" + endTime + '\'' +
                ", flag='" + flag + '\'' +
                ", host='" + host + '\'' +
                ", module='" + module + '\'' +
                ", noticeDescription='" + noticeDescription + '\'' +
                ", noticeId='" + noticeId + '\'' +
                ", noticeType='" + noticeType + '\'' +
                ", system='" + system + '\'' +
                ", time='" + time + '\'' +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
