package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class Confirm {

    private TradeUtil tradeUtil;

    private BuyFeign feign;

    /**
     * --------------点击确认(待确认)-----------
     */
    @Login
    @PostMapping("")
    @Transactional(propagation = Propagation.REQUIRED)
    public R payCoin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestAttribute("uid") String uid) {

        //判断交易密码是否正确
        UserPayPwdEntity userPayPwdEntity = feign.selectUid(uid);
        //交易密码不正确
        if (!pwd.equals(userPayPwdEntity.getPayPassword())) {
            return R.ok().put("code", "3");
        }
        //手续费
        Map<String, Float> map = tradeUtil.poundage(id);
        BigDecimal buyQuantity = new BigDecimal(map.get("buyQuantity"));
        BigDecimal totalPoundage = new BigDecimal(map.get("totalPoundage"));

        //充值
        UserBuyEntity userBuyEntity = feign.selectBuyById(id);
        UserAccountEntity userAccountEntity = feign.selectAccountById(userBuyEntity.getPurchaseUid());
        userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().add(buyQuantity));
        userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().add(buyQuantity));
        feign.updateAccountById(userAccountEntity);

        //添加手续费到user_brokerage中
        UserBrokerageEntity userBrokerageEntity = feign.selectBrokerageById(1);
        userBrokerageEntity.setSellBrokerage(userBrokerageEntity.getSellBrokerage().add(totalPoundage));
        feign.updateBrokerageById(userBrokerageEntity);

        //删除Buy_POUNDAGE
        feign.deletePoundage(id);

        //添加到user_buy_history
        UserBuyHistoryEntity userBuyHistoryEntity = new UserBuyHistoryEntity();
        userBuyHistoryEntity.setFinishTime(new Date());
        userBuyHistoryEntity.setOrderState(ORDER_STATE_FINISH);
        userBuyHistoryEntity.setPurchaseUid(userBuyEntity.getPurchaseUid());
        userBuyHistoryEntity.setSellUid(uid);
        userBuyHistoryEntity.setId(id);
        userBuyHistoryEntity.setQuantity(userBuyEntity.getQuantity());
        userBuyHistoryEntity.setPrice(userBuyEntity.getPrice());
        feign.insertHistory(userBuyHistoryEntity);

        //删除user_buy记录
        feign.deleteBuyById(id);

        return R.ok().put("code", SUCCESS);
    }
}
