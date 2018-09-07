package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.TradePoundageFeign;
import cn.bitflash.vip.buy.feign.UserAccountFeign;
import cn.bitflash.vip.buy.feign.UserBuyFeign;
import cn.bitflash.vip.buy.feign.UserBuyHistoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy/readyToPay")
public class pendingPay {

    @Autowired
    private UserBuyFeign userBuyFeign;

    @Autowired
    private TradePoundageFeign tradePoundageFeign;

    @Autowired
    private UserAccountFeign userAccountFeign;

    @Autowired
    private UserBuyHistoryFeign userBuyHistoryFeign;

    /**
     * --------点击已付款(待付款)-----------
     */
    @PostMapping("pay")
    @Transactional(propagation = Propagation.REQUIRED)
    public R payMoney(@RequestParam("id") String id) {
        UserBuyEntity userBuyEntity = userBuyFeign.selectOne(new ModelMap("id",id));
        //设置支付时间,user_buy订单状态
        userBuyEntity.setPayTime(new Date());
        userBuyEntity.setState(STATE_BUY_ACCCOIN);
        userBuyFeign.updateById(userBuyEntity);
        //修改交易状态
        UserBuyHistoryEntity userBuyHistoryEntity = userBuyHistoryFeign.selectOne(new ModelMap("user_buy_id", id));
        userBuyHistoryEntity.setSellState(STATE_BUY_PAYCOIN);
        userBuyHistoryEntity.setPurchaseState(STATE_BUY_ACCCOIN);
        userBuyHistoryFeign.updateById(userBuyHistoryEntity);
        return R.ok().put("code", SUCCESS);
    }

    /**
     * -------------点击取消(待付款)------------
     */
    @PostMapping("cancel")
    @Transactional(propagation = Propagation.REQUIRED)
    public R recall(@RequestParam("id") String id) {
        //查询uid
        UserBuyHistoryEntity userBuyHistoryEntity = userBuyHistoryFeign.selectOne(new ModelMap("user_buy_id", id));
        //获取trade_poundage手续费，并返还，删除该信息
        TradePoundageEntity tradePoundageEntity = tradePoundageFeign.selectOne(new ModelMap("user_trade_id", id));
        UserAccountEntity userAccountEntity = userAccountFeign.selectOne(new ModelMap("uid", userBuyHistoryEntity.getSellUid()));
        userAccountEntity.setRegulateIncome(tradePoundageEntity.getPoundage().add(tradePoundageEntity.getQuantity()).add(userAccountEntity.getRegulateIncome()));
        userAccountEntity.setAvailableAssets(tradePoundageEntity.getPoundage().add(tradePoundageEntity.getQuantity()).add(userAccountEntity.getAvailableAssets()));
        userAccountFeign.updateById(userAccountEntity);
        tradePoundageFeign.deleteById(id);
        //删除求购历史订单
        userBuyHistoryFeign.deleteById(id);
        userBuyFeign.deleteById(id);
        return R.ok().put("code", SUCCESS);
    }
}
