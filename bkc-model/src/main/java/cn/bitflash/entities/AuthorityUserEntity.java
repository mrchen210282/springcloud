package cn.bitflash.entities;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author wangjun
 * @version 2018年7月30日下午1:49:14
 */
@TableName("authority_user")
public class AuthorityUserEntity implements Serializable {

    @TableId(type = IdType.INPUT)
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
