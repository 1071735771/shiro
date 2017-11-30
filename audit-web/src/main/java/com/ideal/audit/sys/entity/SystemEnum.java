package com.ideal.audit.sys.entity;

import com.ideal.audit.common.entity.AutoModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 系统信息字典实体类
 *
 */
@Entity
@Table(name="system_enum")
public class SystemEnum extends AutoModel {

    @Column(name="value",nullable=true)
    private String value;//值

    @Column(name="describes",nullable=true)
    private String describes;//描述

    @Column(name="groups",nullable=true)
    private String groups;//group

    @Column(name="code",nullable=true)
    private String code;//group

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

