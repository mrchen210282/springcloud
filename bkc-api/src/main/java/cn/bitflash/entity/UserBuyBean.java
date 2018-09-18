/**
 * Copyright 2018 贝莱科技 http://www.bitflash.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author gaoyuguo
 */
public class UserBuyBean extends UserBuyEntity implements Serializable {

    /**
     * 订单号
     */
    private String id;

    /**
     * 求购者id
     */
    private String purchaseUid;

    /**
     * 求购者昵称
     */
    private String purchaseUid;

    /**
     * 求购者状态
     */
    private String purchaseState;

    /**
     * 数量
     */
    private float quantity;

    /**
     * 价格
     */
    private float price;

    /**
     * 卖出者id
     */
    private String sellUid;

    /**
     * 卖出者状态
     */
    private String sellState;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;
}
