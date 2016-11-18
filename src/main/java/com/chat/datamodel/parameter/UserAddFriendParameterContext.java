package com.chat.datamodel.parameter;

/**
 * Created by xiangtianyu on 2016/11/18.
 */
public class UserAddFriendParameterContext {
    private int     uid;

    private int     fid;

    private String  createTime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
