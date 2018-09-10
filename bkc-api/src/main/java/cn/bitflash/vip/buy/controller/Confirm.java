package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.ConfirmFeign;
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

    private ConfirmFeign feign;

    /**
     * --------------点击确认(待确认)-----------
     */
    @Login
    @PostMapping("confirm")
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
        UserBuyHistoryEntity userBuyHistoryEntity = feign.selectHistoryById(id);
        UserAccountEntity userAccountEntity = feign.selectAccountById(userBuyHistoryEntity.getPurchaseUid());
        userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().add(buyQuantity));
        userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().add(buyQuantity));
        feign.updateAccountById(userAccountEntity);

        //添加手续费到user_brokerage中
        UserBrokerageEntity userBrokerageEntity = feign.selectBrokerageById(1);
        userBrokerageEntity.setSellBrokerage(userBrokerageEntity.getSellBrokerage().add(totalPoundage));
        feign.updateBrokerageById(userBrokerageEntity);

        //删除TRADE_POUNDAGE
        feign.deletePoundageById(id);

        //设置结束时间
        userBuyHistoryEntity.setFinishTime(new Date());

        //设置状态
        UserBuyEntity userBuyEntity = feign.selectBuyById(id);
        userBuyEntity.setState(STATE_BUY_FINISH);
        feign.updateById(userBuyEntity);

        //设置状态,交易成功
        userBuyHistoryEntity.setSellState(STATE_BUY_FINISH);
        userBuyHistoryEntity.setPurchaseState(STATE_BUY_FINISH);
        feign.updateHistoryById(userBuyHistoryEntity);
        return R.ok().put("code", SUCCESS);
    }
}
