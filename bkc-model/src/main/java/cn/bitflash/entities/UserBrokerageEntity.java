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

package cn.bitflash.entities;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjun
 */
@TableName("user_brokerage")
public class UserBrokerageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    /**
     * 买家手续费
     */
    private BigDecimal purchaseBrokerage;

    /**
     * 卖家手续费
     */
    private BigDecimal sellBrokerage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPurchaseBrokerage() {
        return purchaseBrokerage;
    }

    public void setPurchaseBrokerage(BigDecimal purchaseBrokerage) {
        this.purchaseBrokerage = purchaseBrokerage;
    }

    public BigDecimal getSellBrokerage() {
        return sellBrokerage;
    }

    public void setSellBrokerage(BigDecimal sellBrokerage) {
        this.sellBrokerage = sellBrokerage;
    }

}
