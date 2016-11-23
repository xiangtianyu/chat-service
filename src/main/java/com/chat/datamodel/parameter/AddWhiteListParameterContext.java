package com.chat.datamodel.parameter;

/**
 * Created by xiangtianyu on 2016/11/22.
 */
public class AddWhiteListParameterContext {
    private int     id;

    private String  ip;

    private int     isAuto;

    private String  sTime;

    private String  eTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(int isAuto) {
        this.isAuto = isAuto;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
