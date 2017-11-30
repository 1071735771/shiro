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
@Table(name ="send", schema = "audit", catalog = "")
public class Send extends AutoModel {
    private String userName;
    private String receiverEmail;
    private String sendState;
    private String sendContent;
    private String sendWay;


    @Basic
    @Column(name = "user_name", nullable = true,length = 300)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "receiver_email", nullable = true,length = 300)
    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @Basic
    @Column(name = "send_state", nullable = true,length = 300)
    public String getSendState() {
        return sendState;
    }

    public void setSendState(String sendState) {
        this.sendState = sendState;
    }


    @Basic
    @Column(name = "send_content", nullable = true,length = 300)
    public String getSendContent() {
        return sendContent;
    }


    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    @Basic
    @Column(name = "send_way", nullable = true,length = 300)
    public String getSendWay() {
        return sendWay;
    }

    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }


}
