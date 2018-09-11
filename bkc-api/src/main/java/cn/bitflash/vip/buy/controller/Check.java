package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryBean;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.CheckFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy/check")
public class Check {

    private TradeUtil tradeUtil;

    @Autowired
    private CheckFeign feign;

    /**
     * -------------查看交易详情-------------
     *
     * @param id 订单id
     * @return 订单详情
     */
    @PostMapping("buying")
    public R showBuyMessagePage(@RequestParam("id") String id) {
        //订单详情
        UserBuyEntity userBuy = feign.selectUsreBuyById(id);
        //判定订单不存在
        if (userBuy == null || !"1".equals(userBuy.getState())) {
            return R.ok().put("code", TRADEMISS);
        }
        //获取手续费
        Map<String, Float> map = tradeUtil.poundage(id);
        return R.ok().put("code", SUCCESS).put("userBuy", userBuy).put("poundage", map.get("poundage") * 100).put("totalMoney", map.get("totalMoney")).put("totalQuantity", map.get("totalQuantity"));
    }


    /**
     * --------------查看订单详情-------------------
     *
     * @param id 订单id
     * @return 订单详情
     */
    @PostMapping("order")
    public R checkOrder(@RequestParam("id") String id) {
        Object userBean = new Object();

        UserBuyHistoryBean userBuyHistoryBean = feign.selectBuyHistory(id);
        if (userBuyHistoryBean == null || "".equals(userBuyHistoryBean)) {
            UserBuyEntity userBuy = feign.selectUsreBuyById(id);
            if (userBuy == null) {
                return R.ok().put("code", "订单不存在");
            }
            userBean = userBuy;
        } else {
            userBean = userBuyHistoryBean;
        }
        Map<String, Float> map = tradeUtil.poundage(id);

        return R.ok().put("userBean", userBean).put("totalQuantity", map.get("totalQuantity")).put("price", map.get("price")).put("buyQuantity", map.get("buyQuantity")).put("totalMoney", map.get("totalMoney"));
    }

}
