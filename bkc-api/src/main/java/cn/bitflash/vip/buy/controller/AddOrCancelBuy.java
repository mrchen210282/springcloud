package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
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
        userBuyEntity.setPurchaseUid(uid);
        userBuyEntity.setCreateTime(new Date());
        userBuyEntity.setPurchaseState(STATE_BUY_PUBLISH);
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
        if (ub == null || !STATE_BUY_PUBLISH.equals(ub.getPurchaseState())) {
            return R.ok().put("code", FAIL);
        }

        //user_buy删除记录
        feign.deleteBuyById(id);

        //user_buy_histoty添加该记录为撤销完成
        UserBuyHistoryEntity userBuyHistoryEntity = new UserBuyHistoryEntity();
        userBuyHistoryEntity.setId(id);
        userBuyHistoryEntity.setOrderState(STATE_BUY_CANCEL);
        userBuyHistoryEntity.setPrice(ub.getPrice());
        userBuyHistoryEntity.setQuantity(ub.getQuantity());
        userBuyHistoryEntity.setPurchaseUid(ub.getPurchaseUid());
        userBuyHistoryEntity.setSellUid(null);
        userBuyHistoryEntity.setFinishTime(new Date());
        feign.insertBuyHistory(userBuyHistoryEntity);

        return R.ok().put("code", SUCCESS);
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

        //查询订单是否存在
        UserBuyEntity userBuy = feign.selectUsreBuyById(id);
        if (userBuy == null || !STATE_BUY_PUBLISH.equals(userBuy.getPurchaseState())) {
            return R.ok().put("code", TRADEMISS);
        }

        //获取手续费
        Map<String, Float> map = tradeUtil.poundage(id);

        //扣除手续费
        boolean dec = tradeUtil.deduct(new BigDecimal(map.get("totalQuantity")), uid);
        if(dec == false){
            return R.ok().put("code", FAIL);
        }

        //添加手续费记录
        BuyPoundageEntity buyPoundageEntity = new BuyPoundageEntity();
        buyPoundageEntity.setUserBuyId(id);
        buyPoundageEntity.setSellUid(uid);
        buyPoundageEntity.setPoundage(new BigDecimal(map.get("totalPoundage")));
        buyPoundageEntity.setQuantity(new BigDecimal(map.get("buyQuantity")));
        buyPoundageEntity.setCreateTime(new Date());
        feign.insertBuyPoundage(buyPoundageEntity);

        //修改user_buy
        userBuy.setSellUid(uid);
        userBuy.setSellState(STATE_BUY_ACCMONEY);
        userBuy.setPurchaseState(STATE_BUY_PAYMONEY);
        feign.updateById(userBuy);

        return R.ok().put("code", SUCCESS);
    }
}
