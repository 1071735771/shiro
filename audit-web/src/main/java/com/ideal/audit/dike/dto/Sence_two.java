package com.ideal.audit.dike.dto;

/**
 * Created by ybb on 2017/11/22 14:21
 *
 * @Description :
 * @ModifyBy:
 */
public class Sence_two {

    private String portalsUser;
    private String Hname;
    private String opTime;
    private String db;
    private String tb;
    private String cmd;

    public Sence_two() {
    }

    public Sence_two(String portalsUser, String hname, String opTime, String db, String tb, String cmd) {
        this.portalsUser = portalsUser;
        Hname = hname;
        this.opTime = opTime;
        this.db = db;
        this.tb = tb;
        this.cmd = cmd;
    }

    public String getPortalsUser() {
        return portalsUser;
    }

    public void setPortalsUser(String portalsUser) {
        this.portalsUser = portalsUser;
    }

    public String getHname() {
        return Hname;
    }

    public void setHname(String hname) {
        Hname = hname;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "Sence_two{" +
                "portalsUser='" + portalsUser + '\'' +
                ", Hname='" + Hname + '\'' +
                ", opTime='" + opTime + '\'' +
                ", db='" + db + '\'' +
                ", tb='" + tb + '\'' +
                ", cmd='" + cmd + '\'' +
                '}';
    }
}
