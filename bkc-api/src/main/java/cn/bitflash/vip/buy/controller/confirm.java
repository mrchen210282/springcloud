package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.R;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class confirm {

    /**
     * --------------点击确认(待确认)-----------
     */
    @Login
    @PostMapping("confirm")
    @Transactional(propagation = Propagation.REQUIRED)
    public R payCoin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestAttribute("uid") String uid) {

        //判断交易密码是否正确
        UserPayPwdEntity userPayPwdEntity = userUtils.selectUserPayPwd(new ModelMap("uid", uid));
        //交易密码不正确
        if (!pwd.equals(userPayPwdEntity.getPayPassword())) {
            return R.ok().put("code", "3");
        }
        //手续费
        Map<String, Float> map = this.poundage(id);
        BigDecimal buyQuantity = new BigDecimal(map.get("buyQuantity"));
        BigDecimal totalPoundage = new BigDecimal(map.get("totalPoundage"));
        //充值
        UserBuyHistoryEntity userBuyHistoryEntity = userBuyHistoryService.selectOne(new EntityWrapper<UserBuyHistoryEntity>().eq("user_buy_id", id));
        UserAccountEntity userAccountEntity = userAccountService.selectOne(new EntityWrapper<UserAccountEntity>().eq("uid", userBuyHistoryEntity.getPurchaseUid()));
        userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().add(buyQuantity));
        userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().add(buyQuantity));
        userAccountService.updateById(userAccountEntity);

        //添加手续费到user_brokerage中
        UserBrokerageEntity userBrokerageEntity = userBrokerageService.selectById(1);
        userBrokerageEntity.setSellBrokerage(userBrokerageEntity.getSellBrokerage().add(totalPoundage));
        userBrokerageService.update(userBrokerageEntity, new EntityWrapper<UserBrokerageEntity>().eq("id", 1));

        //删除TRADE_POUNDAGE
        tradePoundageService.delete(new EntityWrapper<TradePoundageEntity>().eq("user_trade_id", id));

        //设置结束时间
        userBuyHistoryEntity.setFinishTime(new Date());

        //设置状态
        UserBuyEntity userBuyEntity = userBuyService.selectById(id);
        userBuyEntity.setState(STATE_BUY_FINISH);
        userBuyService.updateById(userBuyEntity);

        //设置状态,交易成功
        userBuyHistoryEntity.setSellState(STATE_BUY_FINISH);
        userBuyHistoryEntity.setPurchaseState(STATE_BUY_FINISH);
        userBuyHistoryService.updateById(userBuyHistoryEntity);
        return R.ok().put("code", SUCCESS);
    }
}
