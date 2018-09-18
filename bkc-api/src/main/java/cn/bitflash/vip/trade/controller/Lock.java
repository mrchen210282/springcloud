package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserTradeEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Lock {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("provingState")
    public R provingState(@RequestParam String orderId, @RequestAttribute("uid") String uid) {
        if (StringUtils.isNotBlank(orderId)) {
            String[] str = redisUtils.get(orderId, String[].class);
            if (str == null || str.length == 0) {
                return R.ok().put("code", 200);
            } else if (str[1].equals(uid)) {
                return R.ok().put("code", 400);
            }
            return R.error("订单已锁定");
        }
        return R.error();
    }

    @Login
    @PostMapping("updateTradeState")
    public R updateTradeState() {
        List<UserTradeEntity> trades = tradeFeign.selectTradeByState("5");
        trades.stream().forEach((t) -> {
            String[] str = redisUtils.get(t.getId(), String[].class);
            if (str == null || str.length == 0) {
                if (t.getState().equals(Common.STATE_LOCK)) {
                    t.setState(Common.STATE_SELL);
                    tradeFeign.updateTrade(t);

                    tradeFeign.deleteTrade(t.getId());
                }
            }
        });
        return R.ok();
    }
}
