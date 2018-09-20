package cn.bitflash.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class IndexAppButton implements Serializable {

    private int id;

    private String buttonAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getButtonAddress() {
        return buttonAddress;
    }

    public void setButtonAddress(String buttonAddress) {
        this.buttonAddress = buttonAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
