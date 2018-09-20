package cn.bitflash.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class UserCashIncome implements Serializable {

    private String uid;

    private float totel_income;

    private float daily_income;

    private String stage_head;

    private String share_benefit;

    private float power;

    private float total_income;

    private String line_f;

    private String line_s;

    private String line_t;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getTotel_income() {
        return totel_income;
    }

    public void setTotel_income(float totel_income) {
        this.totel_income = totel_income;
    }

    public float getDaily_income() {
        return daily_income;
    }

    public void setDaily_income(float daily_income) {
        this.daily_income = daily_income;
    }

    public String getStage_head() {
        return stage_head;
    }

    public void setStage_head(String stage_head) {
        this.stage_head = stage_head;
    }

    public String getShare_benefit() {
        return share_benefit;
    }

    public void setShare_benefit(String share_benefit) {
        this.share_benefit = share_benefit;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getTotal_income() {
        return total_income;
    }

    public void setTotal_income(float total_income) {
        this.total_income = total_income;
    }

    public String getLine_f() {
        return line_f;
    }

    public void setLine_f(String line_f) {
        this.line_f = line_f;
    }

    public String getLine_s() {
        return line_s;
    }

    public void setLine_s(String line_s) {
        this.line_s = line_s;
    }

    public String getLine_t() {
        return line_t;
    }

    public void setLine_t(String line_t) {
        this.line_t = line_t;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
