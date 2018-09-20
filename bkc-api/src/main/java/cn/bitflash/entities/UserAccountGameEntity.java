package cn.bitflash.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangjun
 * @version 2018年8月10日下午3:42:36
 */
public class UserAccountGameEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3272015324293238491L;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 购买数量
     */
    private BigDecimal purchase;

    /**
     * 赠送数量
     */
    private BigDecimal giveAmount;

    /**
     * 报单总数 （实际报单数+赠送量）
     */
    private BigDecimal totelAssets;

    /**
     * 冻结资产
     */
    private BigDecimal frozenAssets;

    private BigDecimal purchase_release;

    private BigDecimal give_amount_release;

    private BigDecimal daily_release;

    private BigDecimal totel_release;

    /**
     * 调节释放
     */
    private BigDecimal regulateRelease;

    /**
     * 可用资产
     */
    private BigDecimal availableAssets;

    /**
     * 调节收益
     */
    private BigDecimal regulateIncome;

    /**
     * 总收益
     */
    private BigDecimal totelIncome;

    /**
     * 每日收益
     */
    private BigDecimal dailyIncome;

    /**
     * 左区业绩
     */
    private BigDecimal lftAchievement;

    /**
     * 右区业绩
     */
    private BigDecimal rgtAchievement;

    /**
     * 报单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getGiveAmount() {
        return giveAmount;
    }

    public void setGiveAmount(BigDecimal giveAmount) {
        this.giveAmount = giveAmount;
    }

    public BigDecimal getTotelAssets() {
        return totelAssets;
    }

    public void setTotelAssets(BigDecimal totelAssets) {
        this.totelAssets = totelAssets;
    }

    public BigDecimal getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(BigDecimal frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public BigDecimal getPurchase_release() {
        return purchase_release;
    }

    public void setPurchase_release(BigDecimal purchase_release) {
        this.purchase_release = purchase_release;
    }

    public BigDecimal getGive_amount_release() {
        return give_amount_release;
    }

    public void setGive_amount_release(BigDecimal give_amount_release) {
        this.give_amount_release = give_amount_release;
    }

    public BigDecimal getDaily_release() {
        return daily_release;
    }

    public void setDaily_release(BigDecimal daily_release) {
        this.daily_release = daily_release;
    }

    public BigDecimal getTotel_release() {
        return totel_release;
    }

    public void setTotel_release(BigDecimal totel_release) {
        this.totel_release = totel_release;
    }

    public BigDecimal getRegulateRelease() {
        return regulateRelease;
    }

    public void setRegulateRelease(BigDecimal regulateRelease) {
        this.regulateRelease = regulateRelease;
    }

    public BigDecimal getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(BigDecimal availableAssets) {
        this.availableAssets = availableAssets;
    }

    public BigDecimal getRegulateIncome() {
        return regulateIncome;
    }

    public void setRegulateIncome(BigDecimal regulateIncome) {
        this.regulateIncome = regulateIncome;
    }

    public BigDecimal getTotelIncome() {
        return totelIncome;
    }

    public void setTotelIncome(BigDecimal totelIncome) {
        this.totelIncome = totelIncome;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public BigDecimal getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(BigDecimal lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public BigDecimal getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(BigDecimal rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
