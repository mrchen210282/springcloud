package cn.bitflash.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 申诉
 *
 * @author gaoyuguo
 */
public class UserComplaintEntity implements Serializable {

    //订单id
    private String orderId;

    //申拆人uid
    private String complaintUid;

    //联系人uid
    private String contactsUid;

    //申拆状态
    private String complaintState;

    //订单状态
    private String orderState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComplaintUid() {
        return complaintUid;
    }

    public void setComplaintUid(String complaintUid) {
        this.complaintUid = complaintUid;
    }

    public String getComplaintState() {
        return complaintState;
    }

    public void setComplaintState(String complaintState) {
        this.complaintState = complaintState;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContactsUid() {
        return contactsUid;
    }

    public void setContactsUid(String contactsUid) {
        this.contactsUid = contactsUid;
    }
}
