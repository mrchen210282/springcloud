package cn.bitflash.entities;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("user_digital_income")
public class UserDigitalIncome implements Serializable {
    @TableId
    private String uid;

    private float purchase;

    private float frozenAssets;

    private float purchaseRelease;

    private float dailyRelease;

    private float totelRelease;

    private float availableAssets;

    private float lftAchievement;

    private float rgtAchievement;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getPurchase() {
        return purchase;
    }

    public void setPurchase(float purchase) {
        this.purchase = purchase;
    }

    public float getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(float frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public float getPurchaseRelease() {
        return purchaseRelease;
    }

    public void setPurchaseRelease(float purchaseRelease) {
        this.purchaseRelease = purchaseRelease;
    }

    public float getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(float dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public float getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(float totelRelease) {
        this.totelRelease = totelRelease;
    }

    public float getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(float availableAssets) {
        this.availableAssets = availableAssets;
    }

    public float getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(float lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public float getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(float rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
