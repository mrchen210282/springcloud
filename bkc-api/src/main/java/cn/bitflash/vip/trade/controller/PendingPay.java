/*
package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserTradeEntity;
import cn.bitflash.entity.UserTradeHistoryEntity;
import cn.bitflash.util.Common;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class PendingPay {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeFeign tradeFeign;

    @Autowired
    private RedisUtils redisUtils;

    @Login
    @PostMapping("payTrade")
    public R payTrade(@RequestParam String orderId, @RequestAttribute("uid")String uid) {

        logger.info("方法payTrade，订单号:" + orderId);
        if(StringUtils.isNotBlank(orderId)) {
            // 更新为已购买
            UserTradeEntity userTradeEntity = new UserTradeEntity();
            userTradeEntity.setId(orderId);
            userTradeEntity.setState(Common.STATE_CONFIRM);
            tradeFeign.updateTrade(userTradeEntity);
            //更新历史记录
            UserTradeHistoryEntity userTradeHistory = tradeFeign.selectTradeHistoryById(orderId);
            userTradeHistory.setState(Common.STATE_CONFIRM);
            tradeFeign.updateUserTradeHistory(userTradeHistory);
             return R.ok();
        }
        return R.error();
    }

    @Login
    @PostMapping("cancelOrder")
    @Transactional
    public R cancelOrder(@RequestParam String orderId) {
        if (StringUtils.isNotBlank(orderId)) {
            logger.info("取消订单号:" + orderId);

            redisUtils.delete(orderId);

            // 更新
            UserTradeEntity userTradeEntity = new UserTradeEntity();
            userTradeEntity.setId(orderId);
            userTradeEntity.setState(Common.STATE_SELL);
            tradeFeign.insertOrUpdateTrade(userTradeEntity);

            //删除历史记录
            tradeFeign.delUserTradeById(orderId);
        }
        return R.ok();
    }
}
*/
