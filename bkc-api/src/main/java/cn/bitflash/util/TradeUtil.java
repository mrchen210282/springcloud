package cn.bitflash.util;

import cn.bitflash.entity.UserBuyEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class TradeUtil {

    /**
     * ----------------------------手续费+订单数量------------------------
     */
    public Map<String, Float> poundage(String id) {
        UserBuyEntity userBuy = userBuyService.selectById(id);
        if (userBuy == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#########.##");
        //交易数量
        Float buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
        //手续费比率
        Float poundage = userTradeConfigService.selectOne(new EntityWrapper<UserTradeConfigEntity>().eq("remark", "交易手续费")).getPoundage();
        //Float poundage = Float.parseFloat(df.format(poundagePer));
        //手续费数量
        Float totalPoundage = buyQuantity * poundage;
        //实际交易总数量
        Float totalQuantity = buyQuantity + totalPoundage;
        //单价
        Float price = userBuy.getPrice();
        //总价格
        Float totalMoney = buyQuantity * (price);

        Map<String, Float> map = new HashMap<String, Float>();
        map.put("buyQuantity", buyQuantity);
        map.put("poundage", poundage);
        map.put("totalPoundage", totalPoundage);
        map.put("totalQuantity", totalQuantity);
        map.put("price", price);
        map.put("totalMoney", totalMoney);
        return map;
    }

    /**
     * --------------------------------扣款-------------------------------
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public R deduct(BigDecimal money, String uid) {
        UserAccountEntity userAccountEntity = userAccountService.selectOne(new EntityWrapper<UserAccountEntity>().eq("uid", uid));
        if (userAccountEntity.getAvailableAssets().compareTo(money) != -1) {
            if (userAccountEntity.getRegulateRelease().compareTo(money) != -1) {
                userAccountEntity.setRegulateRelease(userAccountEntity.getRegulateRelease().subtract(money));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
                userAccountService.update(userAccountEntity, new EntityWrapper<UserAccountEntity>().eq("uid", uid));
            } else {
                userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().subtract(money.subtract(userAccountEntity.getRegulateRelease())));
                userAccountEntity.setRegulateRelease(new BigDecimal(0));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
                userAccountService.update(userAccountEntity, new EntityWrapper<UserAccountEntity>().eq("uid", uid));
            }
            return R.ok();
        }
        return R.error("资金不足，扣款失败");
    }
}
