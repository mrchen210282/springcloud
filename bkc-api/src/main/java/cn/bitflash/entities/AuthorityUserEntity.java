package cn.bitflash.entities;

import java.io.Serializable;

/**
 * @author wangjun
 * @version 2018年7月30日下午1:49:14
 */
public class AuthorityUserEntity implements Serializable {

    private String uid;

    private String ticket;

    private String mobile;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
