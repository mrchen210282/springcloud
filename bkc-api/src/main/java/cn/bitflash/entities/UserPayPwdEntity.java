package cn.bitflash.entities;
import java.io.Serializable;
import java.util.Date;

public class UserPayPwdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private String payPassword;

    private Date createTime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
