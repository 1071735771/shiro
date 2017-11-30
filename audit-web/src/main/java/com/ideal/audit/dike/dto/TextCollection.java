package com.ideal.audit.dike.dto;

import org.omg.CORBA.StringHolder;

/**
 * Created by ybb on 2017/11/28 14:20
 *
 * @Description :
 * @ModifyBy:
 */
public class TextCollection {

    private String username;
    private String optime;
    private int syscode;
    private int btcode;
    private int mdcode;
    private String optype;
    private String opinfo;
    private String opresult;
    private String ip;

    public TextCollection() {
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

    public int getSyscode() {
        return syscode;
    }

    public void setSyscode(int syscode) {
        this.syscode = syscode;
    }

    public int getBtcode() {
        return btcode;
    }

    public void setBtcode(int btcode) {
        this.btcode = btcode;
    }

    public int getMdcode() {
        return mdcode;
    }

    public void setMdcode(int mdcode) {
        this.mdcode = mdcode;
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }

    public String getOpinfo() {
        return opinfo;
    }

    public void setOpinfo(String opinfo) {
        this.opinfo = opinfo;
    }

    public String getOpresult() {
        return opresult;
    }

    public void setOpresult(String opresult) {
        this.opresult = opresult;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
