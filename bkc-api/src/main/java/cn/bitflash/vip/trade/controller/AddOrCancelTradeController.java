package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 获取用户交易接口
 *
 * @author wangjun
 * @version 2018年7月4日上午9:44:17
 */
@RestController
@RequestMapping("/api")
public class AddOrCancelTradeController {

     

    /**
     * 添加卖出记录
     *
     * @param userAccount
     * @param quantity
     * @param price
     * @return
     */
//    @Login
//    @PostMapping("publish")
//    @Transactional
//    public R publish(@UserAccount UserAccountEntity userAccount, @RequestParam String quantity, @RequestParam String price) {
//
//        // 先校验出售数量是否大于已有数量
//        BigDecimal total = userAccount.getAvailableAssets();
//        logger.info("uid:" + userAccount.getUid() + ",卖出数量:" + quantity + ",价格:" + price);
//        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(quantity)) {
//
//            BigDecimal priceB = new BigDecimal(price);
//            BigDecimal minPrice = new BigDecimal(Common.MIN_PRICE);
//            if (priceB.compareTo(minPrice) <= -1) {
//                return R.error("最低价格为0.33!");
//            }
//            if (Integer.valueOf(quantity) <= 0) {
//                return R.error("发布数量最小为100!");
//            }
//            BigDecimal quantityB = new BigDecimal(quantity);
//
//            // 卖出数量
//            double quantityD = Double.parseDouble(quantity);
//            //必须为100的整数倍
//            if (quantityD % 100 == 0) {
//
//                UserTradeConfigEntity userTradeConfigEntity = userTradeConfigService.selectOne(new EntityWrapper<UserTradeConfigEntity>().eq("id", "1"));
//                if (null != userTradeConfigEntity) {
//
//                    float poundage = userTradeConfigEntity.getPoundage();
//                    logger.info("poundage:" + poundage);
//                    // 手续费 = 卖出数量 * 0.05
//                    double percent = quantityD * poundage;
//
//                    BigDecimal percentB = new BigDecimal(Math.floor(percent));
//                    // 总卖出数量 = 卖出数量 + 手续费
//                    BigDecimal purchase = quantityB.add(percentB);
//                    // 等于1表示total大于percentB,可以交易
//                    if (total.compareTo(purchase) == 1 || total.compareTo(purchase) == 0) {
//                        // 手续费 = 总可用-百分比
////                        UserBrokerageEntity userBrokerageEntity = userBrokerageService.selectById("1");
////                        if (null != userBrokerageEntity) {
////                            BigDecimal sellBrokerage = userBrokerageEntity.getSellBrokerage();
////                            BigDecimal brokerage = sellBrokerage.add(percentB);
////                            userBrokerageEntity.setSellBrokerage(brokerage);
////                            userBrokerageService.insertOrUpdate(userBrokerageEntity);
//
//                        // 2.卖出数量
//                        BigDecimal quantityBig = new BigDecimal(quantity);
//                        // 卖出量-调节释放
//                        BigDecimal subtract = quantityBig.subtract(userAccount.getRegulateRelease());
//                        if (subtract.doubleValue() > 0 || subtract.doubleValue() == 0) {
//                            userAccount.setRegulateRelease(new BigDecimal(0.00));
//                            BigDecimal regulateIncome = userAccount.getRegulateIncome().subtract(subtract);
//                            userAccount.setRegulateIncome(regulateIncome.subtract(percentB));
//                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
//                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
//                        } else {
//                            BigDecimal release = quantityBig.add(percentB);
//                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
//                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
//                            BigDecimal RegulatRelease = userAccount.getRegulateRelease().subtract(release);
//                            userAccount.setRegulateRelease(RegulatRelease);
//                        }
//                        userAccountService.updateById(userAccount);
//
//                        // 添加卖出记录
//                        UserTradeEntity userTrade = new UserTradeEntity();
//                        String orderId = Common.randomUtil();
//                        userTrade.setId(orderId);
//                        userTrade.setPrice(new BigDecimal(price));
//                        userTrade.setState(Common.STATE_SELL);
//                        userTrade.setQuantity(new BigDecimal(quantity));
//                        userTrade.setUid(userAccount.getUid());
//                        userTrade.setCreateTime(new Date());
//                        userTradeService.insertUserTrade(userTrade);
//
//                        // 1.先扣除手续费，可用于撤消
//                        TradePoundageEntity tradePoundageEntity = new TradePoundageEntity();
//                        tradePoundageEntity.setUserTradeId(orderId);
//                        tradePoundageEntity.setUid(userAccount.getUid());
//                        tradePoundageEntity.setPoundage(percentB);
//                        tradePoundageEntity.setCreateTime(new Date());
//                        tradePoundageService.insert(tradePoundageEntity);
//                        return R.ok();
//                    } else {
//                        return R.error().put("code", "1");
//                    }
//                }
//
//            } else {
//                return R.error("卖出数量必须为100的倍数！");
//            }
//        } else {
//            return R.error("参数不能为空！");
//        }
//        return R.ok();
//    }
}
