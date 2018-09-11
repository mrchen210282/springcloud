package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.TradeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class TradeUtil {

@Autowired
private TradeFeign feign;

    /**
     * ----------------------------手续费+订单数量------------------------
     */
    public Map<String, Float> poundage(String id) {
        UserBuyEntity userBuy = feign.selectBuyById(id);
        if (userBuy == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#########.##");
        //交易数量
        Float buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
        //手续费比率
        Float poundage = feign.selectOne(new ModelMap("remark", "交易手续费")).getPoundage();
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
        UserAccountEntity userAccountEntity = feign.selectAccountById(uid);
        if (userAccountEntity.getAvailableAssets().compareTo(money) != -1) {
            if (userAccountEntity.getRegulateRelease().compareTo(money) != -1) {
                userAccountEntity.setRegulateRelease(userAccountEntity.getRegulateRelease().subtract(money));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
            } else {
                userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().subtract(money.subtract(userAccountEntity.getRegulateRelease())));
                userAccountEntity.setRegulateRelease(new BigDecimal(0));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
            }
            feign.updateAccountById(userAccountEntity);
            return R.ok();
        }
        return R.error("资金不足，扣款失败");
    }
}
