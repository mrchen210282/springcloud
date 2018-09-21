///**
// * Copyright 2018 贝莱科技 http://www.bitflash.cn
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy of
// * the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations under
// * the License.
// */
//
//package cn.bitflash.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 求购
// *
// * @author gaoyuguo
// */
//public class UserBuyBean implements Serializable {
//
//    /**
//     * 订单号
//     */
//    private String id;
//
//    /**
//     * 求购者id
//     */
//    private String purchaseUid;
//
//    /**
//     * 求购者昵称
//     */
//    private String purchaseNickname;
//
//    /**
//     * 求购者电话
//     */
//    private String purMobile;
//
//    /**
//     * 数量
//     */
//    private float quantity;
//
//    /**
//     * 价格
//     */
//    private float price;
//
//    /**
//     * 卖出者id
//     */
//    private String sellUid;
//
//    /**
//     * 卖出者昵称
//     */
//    private String sellNickname;
//
//    /**
//     * 卖出者电话
//     */
//    private String sellMobile;
//
//    /**
//     * 发布时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createTime;
//
//    /**
//     * 支付时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date payTime;
//
//    /**
//     * 昵称
//     * @return
//     */
//    private String nickname;
//
//    /**
//     * 状态
//     * @return
//     */
//    private String state;
//
//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getPurchaseUid() {
//        return purchaseUid;
//    }
//
//    @Override
//    public void setPurchaseUid(String purchaseUid) {
//        this.purchaseUid = purchaseUid;
//    }
//
//    public String getPurchaseNickname() {
//        return purchaseNickname;
//    }
//
//    public void setPurchaseNickname(String purchaseNickname) {
//        this.purchaseNickname = purchaseNickname;
//    }
//
//    @Override
//    public float getQuantity() {
//        return quantity;
//    }
//
//    @Override
//    public void setQuantity(float quantity) {
//        this.quantity = quantity;
//    }
//
//    @Override
//    public float getPrice() {
//        return price;
//    }
//
//    @Override
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    @Override
//    public String getSellUid() {
//        return sellUid;
//    }
//
//    @Override
//    public void setSellUid(String sellUid) {
//        this.sellUid = sellUid;
//    }
//
//    public String getSellNickname() {
//        return sellNickname;
//    }
//
//    public void setSellNickname(String sellNickname) {
//        this.sellNickname = sellNickname;
//    }
//
//    @Override
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    @Override
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getPayTime() {
//        return payTime;
//    }
//
//    public void setPayTime(Date payTime) {
//        this.payTime = payTime;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getPurMobile() {
//        return purMobile;
//    }
//
//    public void setPurMobile(String purMobile) {
//        this.purMobile = purMobile;
//    }
//
//    public String getSellMobile() {
//        return sellMobile;
//    }
//
//    public void setSellMobile(String sellMobile) {
//        this.sellMobile = sellMobile;
//    }
//}
