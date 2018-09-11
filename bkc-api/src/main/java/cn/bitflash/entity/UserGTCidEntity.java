package cn.bitflash.entity;

import java.io.Serializable;
import java.util.Date;

public class UserGTCidEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String uid;

    private String cid;

    private Date updateTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}