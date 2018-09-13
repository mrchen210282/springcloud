package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

/**
 * 获取用户交易接口
 *
 * @author wangjun
 * @version 2018年7月4日上午9:44:17
 */
public class AddOrCancel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeFeign tradeFeign;

    @Autowired
    private RedisUtils redisUtils;


    @Login
    @PostMapping("publish")
    @Transactional
    public R publish(@RequestAttribute("uid") String uid, @RequestParam String quantity, @RequestParam String price) {

        UserAccountEntity userAccount = tradeFeign.selectAccountByUid(uid);
        // 先校验出售数量是否大于已有数量
        BigDecimal total = userAccount.getAvailableAssets();
        logger.info("uid:" + userAccount.getUid() + ",卖出数量:" + quantity + ",价格:" + price);
        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(quantity)) {

            BigDecimal priceB = new BigDecimal(price);
            BigDecimal minPrice = new BigDecimal(Common.MIN_PRICE);
            if (priceB.compareTo(minPrice) <= -1) {
                return R.error("最低价格为0.33!");
            }
            if (Integer.valueOf(quantity) <= 0) {
                return R.error("发布数量最小为100!");
            }
            BigDecimal quantityB = new BigDecimal(quantity);

            // 卖出数量
            double quantityD = Double.parseDouble(quantity);
            //必须为100的整数倍
            if (quantityD % 100 == 0) {

                UserTradeConfigEntity userTradeConfigEntity = tradeFeign.selectTradeConfigById(1);
                if (null != userTradeConfigEntity) {

                    float poundage = userTradeConfigEntity.getPoundage();
                    logger.info("poundage:" + poundage);
                    // 手续费 = 卖出数量 * 0.05
                    double percent = quantityD * poundage;

                    BigDecimal percentB = new BigDecimal(Math.floor(percent));
                    // 总卖出数量 = 卖出数量 + 手续费
                    BigDecimal purchase = quantityB.add(percentB);
                    // 等于1表示total大于percentB,可以交易
                    if (total.compareTo(purchase) == 1 || total.compareTo(purchase) == 0) {
                        // 2.卖出数量
                        BigDecimal quantityBig = new BigDecimal(quantity);
                        // 卖出量-调节释放
                        BigDecimal subtract = quantityBig.subtract(userAccount.getRegulateRelease());
                        if (subtract.doubleValue() > 0 || subtract.doubleValue() == 0) {
                            userAccount.setRegulateRelease(new BigDecimal(0.00));
                            BigDecimal regulateIncome = userAccount.getRegulateIncome().subtract(subtract);
                            userAccount.setRegulateIncome(regulateIncome.subtract(percentB));
                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
                        } else {
                            BigDecimal release = quantityBig.add(percentB);
                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
                            BigDecimal RegulatRelease = userAccount.getRegulateRelease().subtract(release);
                            userAccount.setRegulateRelease(RegulatRelease);
                        }
                        tradeFeign.updateUserAccount(userAccount);

                        // 添加卖出记录
                        UserTradeEntity userTrade = new UserTradeEntity();
                        String orderId = Common.randomUtil();
                        userTrade.setId(orderId);
                        userTrade.setPrice(new BigDecimal(price));
                        userTrade.setState(Common.STATE_SELL);
                        userTrade.setQuantity(new BigDecimal(quantity));
                        userTrade.setUid(userAccount.getUid());
                        userTrade.setCreateTime(new Date());
                        tradeFeign.insertOrUpdateTrade(userTrade);

                        // 1.先扣除手续费，可用于撤消
                        TradePoundageEntity tradePoundageEntity = new TradePoundageEntity();
                        tradePoundageEntity.setUserTradeId(orderId);
                        tradePoundageEntity.setUid(userAccount.getUid());
                        tradePoundageEntity.setPoundage(percentB);
                        tradePoundageEntity.setCreateTime(new Date());
                        tradeFeign.insertTradePoundage(tradePoundageEntity);
                        return R.ok();
                    } else {
                        return R.error().put("code", "1");
                    }
                }

            } else {
                return R.error("卖出数量必须为100的倍数！");
            }
        } else {
            return R.error("参数不能为空！");
        }
        return R.ok();
    }

    @Login
    @PostMapping("responseTrade")
    public cn.bitflash.utils.R responseTrade(@RequestAttribute("uid") String uid) {

        UserAccountEntity userAccount = tradeFeign.selectAccountByUid(uid);

        UserAccountEntity userAccountEntity = tradeFeign.selectAccountByUid(userAccount.getUid());
        Map<String, Object> returnMap = null;
        if (null != userAccountEntity) {
            returnMap = tradeFeign.responseTrade(userAccount.getUid());
            //可卖份数 = 可用额度 / 100
            if (userAccount.getAvailableAssets().compareTo(new BigDecimal(0)) <= -1) {
                returnMap.put("number", "0");
            } else {
                // BigDecimal bigDecimal = userAccount.getAvailableAssets().divide(new BigDecimal(Common.MIN_PRICE));
                double number = Double.valueOf(userAccount.getAvailableAssets().toString()) / Common.MIN_NUMBER;
                returnMap.put("number", number);
            }

            String availableAssets = Common.decimalFormat(Double.valueOf(userAccount.getAvailableAssets().toString()));
            returnMap.put("availableAssets", availableAssets);
        }
        return cn.bitflash.utils.R.ok().put("userAccount", returnMap);
    }

    @Login
    @PostMapping("addLock")
    public cn.bitflash.utils.R addLock(@RequestParam String orderId, @RequestAttribute("uid") String uid) throws ParseException {
        logger.info("下单addLock:订单号" + orderId);
        if (StringUtils.isNotBlank(orderId)) {
            UserTradeEntity userTradeEntity = tradeFeign.selectTradeById(orderId);
            if (userTradeEntity.getState().equals(Common.STATE_CANCEL)) {
                return cn.bitflash.utils.R.error(501, "订单已经被撤销,无法锁定");
            }
            String countKey = Common.COUNT_LOCK + uid;
            logger.debug("当前锁定订单的数量为：" + redisUtils.get(countKey));
            Integer count = redisUtils.get(countKey, Integer.class) == null ? 0 : redisUtils.get(countKey, Integer.class);
            if (count < 3) {
                String[] str = redisUtils.get(orderId, String[].class);
                if (str == null || str.length == 0) {

                    //加入历史记录
                    UserTradeHistoryEntity userTradeHistory = new UserTradeHistoryEntity();
                    userTradeHistory.setUserTradeId(orderId);
                    userTradeHistory.setSellUid(userTradeEntity.getUid());
                    userTradeHistory.setSellQuantity(userTradeEntity.getQuantity());
                    userTradeHistory.setPrice(userTradeEntity.getPrice());
                    userTradeHistory.setPurchaseUid(uid);
                    userTradeHistory.setPurchaseQuantity(userTradeEntity.getQuantity());
                    userTradeHistory.setCreateTime(new Date());
                    userTradeHistory.setState(Common.STATE_LOCK);
                    tradeFeign.insertUserTradeHistory(userTradeHistory);

                    //更新订单状态
                    userTradeEntity.setState(Common.STATE_LOCK);
                    tradeFeign.updateTrade(userTradeEntity);

                    //统计锁定数量
                    str = new String[2];
                    str[0] = orderId;
                    str[1] = uid;
                    redisUtils.set(orderId, str, 60 * 60);
                    //当天时间凌晨23:59:59的秒数
                    long tomorrow = LocalDateTime.now().withHour(23)
                            .withMinute(59)
                            .withSecond(59).toEpochSecond(ZoneOffset.of("+8"));
                    //当前时间秒数
                    long now = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                    //设置过期时间为当天剩余时间的秒数
                    redisUtils.set(countKey, ++count, (tomorrow - now));

                    return cn.bitflash.utils.R.ok();
                } else if (str[1].equals(uid)) {
                    return cn.bitflash.utils.R.error(502, "订单被锁定,本人锁定");
                }
                return cn.bitflash.utils.R.error(503, "订单已经被锁定");
            }
            return cn.bitflash.utils.R.error(504, "当天锁定数量已到上限");
        }
        return cn.bitflash.utils.R.error(505, "无此订单信息");


    }
}
