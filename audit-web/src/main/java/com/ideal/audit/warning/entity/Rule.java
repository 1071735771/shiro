package com.ideal.audit.warning.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by chu on 2017/9/19.
 */
@Entity
@Table(name ="rule", schema = "audit", catalog = "")
public class Rule extends AutoModel {
    private Integer intervalTime;
    private Integer errorTryNumber;
    private Integer commonNumber;
    private String sendEmail;

    @Basic
    @Column(name = "interval_time", nullable = true)
    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
    @Basic
    @Column(name = "error_try_number", nullable = true)
    public Integer getErrorTryNumber() {
        return errorTryNumber;
    }

    public void setErrorTryNumber(int errorTryNumber) {
        this.errorTryNumber = errorTryNumber;
    }
    @Basic
    @Column(name = "common_number", nullable = true)
    public Integer getCommonNumber() {
        return commonNumber;
    }

    public void setCommonNumber(int commonNumber) {
        this.commonNumber = commonNumber;
    }
    @Basic
    @Column(name = "send_email", nullable = true)
    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

}
