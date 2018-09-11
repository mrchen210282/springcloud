package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.AddOrCancelFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class AddOrCancelBuy {

    private TradeUtil tradeUtil;

    @Autowired
    private AddOrCancelFeign feign;

    /**
     * --------------发布---------------
     *
     * @param userBuyEntity 订单信息
     * @return 交易状态
     */
    @Login
    @PostMapping("publish")
    public R addBuyMessage(@RequestBody UserBuyEntity userBuyEntity, @RequestAttribute("uid") String uid) {
        if (userBuyEntity == null) {
            return R.error(501, "求购信息为空");
        }
        Float num = userBuyEntity.getQuantity();
        if (num % 100 != 0 || num < 100) {
            return R.error(502, "求购数量最低为100，且为100的倍数");
        }

        String orderId = Common.randomUtil();
        userBuyEntity.setId(orderId);
        userBuyEntity.setUid(uid);
        userBuyEntity.setCreateTime(new Date());
        userBuyEntity.setState(STATE_BUY_CANCEL);
        feign.insertBuy(userBuyEntity);
        return R.ok().put("code", SUCCESS);
    }

    /**
     * --------------点击撤销--------------
     */
    @PostMapping("recall")
    @Transactional(propagation = Propagation.REQUIRED)
    public R cancelBuyMessage(@RequestParam String id) {
        UserBuyEntity ub = feign.selectUsreBuyById(id);
        if (ub == null || STATE_BUY_CANCELFIISH.equals(ub.getState())) {
            return R.ok().put("code", FAIL);
        }
        if (ub.getState().equals(STATE_BUY_CANCEL)) {
            ub.setState(STATE_BUY_CANCELFIISH);
            ub.setCancelTime(new Date());
            feign.updateById(ub);
            return R.ok().put("code", SUCCESS);
        }
        return R.ok();
    }

    /**
     * ---------------下单----------------
     *
     * @param id 订单id
     * @return 交易状态
     * <p>
     * 1.查询手续费，并从卖出者账号中扣除。如资金不足抛出错误
     * 2.添加手续费到trade_poundage
     * 3.修改user_buy中state为‘1’交易中状态
     * 4.添加订单到user_buy_history
     * 5.修改求购者
     */
    @Login
    @PostMapping("click")
    @Transactional(propagation = Propagation.REQUIRED)
    public R addBuyMessageHistory(@RequestParam("id") String id, @RequestAttribute("uid") String uid) {

        //交易状态:'1'资金不足,'2'订单不存在
        UserBuyEntity userBuy = feign.selectUsreBuyById(id);
        if (userBuy == null || !STATE_BUY_CANCEL.equals(userBuy.getState())) {
            return R.ok().put("code", TRADEMISS);
        }

        //获取手续费
        Map<String, Float> map = tradeUtil.poundage(id);
        //扣除手续费
        UserAccountEntity userAccountEntity = feign.selectAccountById(uid);
        //不足支付扣款
        if (new BigDecimal(map.get("totalQuantity")).compareTo(userAccountEntity.getAvailableAssets()) == 1) {
            return R.ok().put("code", FAIL);
        }
        tradeUtil.deduct(new BigDecimal(map.get("totalQuantity")), uid);

        //添加手续费记录
        TradePoundageEntity tradePoundageEntity = new TradePoundageEntity();
        tradePoundageEntity.setCreateTime(new Date());
        tradePoundageEntity.setPoundage(new BigDecimal(map.get("totalPoundage")));
        tradePoundageEntity.setUid(uid);
        tradePoundageEntity.setUserTradeId(id);
        tradePoundageEntity.setQuantity(new BigDecimal(map.get("buyQuantity")));
        feign.insertRTradePoundage(tradePoundageEntity);

        //修改user_buy订单状态
        userBuy.setState(STATE_BUY_PAYMONEY);
        feign.updateById(userBuy);

        //添加订单到user_buy_history
        UserBuyHistoryEntity buyHistory = new UserBuyHistoryEntity();
        buyHistory.setPrice(new BigDecimal(userBuy.getPrice()));
        buyHistory.setPurchaseState(STATE_BUY_PAYMONEY);
        buyHistory.setPurchaseUid(userBuy.getUid());
        buyHistory.setQuantity(new BigDecimal(userBuy.getQuantity()));
        buyHistory.setSellState(STATE_BUY_ACCMONEY);
        buyHistory.setSellUid(uid);
        buyHistory.setUserBuyId(id);
        feign.insertBuyHistory(buyHistory);

        return R.ok().put("code", SUCCESS);
    }
}
