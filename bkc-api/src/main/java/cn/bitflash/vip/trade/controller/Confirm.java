package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

public class Confirm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("purchase")
    public R purchase(@RequestAttribute("uid") String uid, @RequestParam String orderId, @RequestParam String payPwd) {

        UserPayPwdEntity userPayPwd = tradeFeign.selectUserPwdByUid(uid);
        if (userPayPwd.getPayPassword().equals(payPwd)) {
            if (StringUtils.isNotBlank(orderId)) {

                logger.info("购买人:" + uid + ",订单号:" + orderId);
                // 查询订单状态为已锁定(state:5)
             /*   Map<String, Object> param = new HashMap<String, Object>();
                param.put("id", orderId);*/
                UserTradeEntity userTradeBean = tradeFeign.selectTradeById(orderId);

                if (null != userTradeBean) {
                    // 订单状态变为已付款
                    userTradeBean.setState(Common.STATE_PAY);
                    userTradeBean.setId(orderId);
                   // param.put("state", Common.STATE_PAY);
                   // param.put("finishTime", new Date());
                    UserTradeEntity userTradeEntity = new UserTradeEntity();
                    userTradeEntity.setState(Common.STATE_PAY);
                    userTradeEntity.setFinishTime(new Date());
                    userTradeEntity.setId(orderId);
                    tradeFeign.insertOrUpdateTrade(userTradeEntity);

                    // 更新购买者收益
                    BigDecimal quantity = userTradeBean.getQuantity();

                    // 查询购买人信息
                    UserTradeHistoryEntity userTradeHistoryEntity = tradeFeign.selectTradeHistoryById(orderId);

                    if (null != userTradeHistoryEntity) {
                        UserAccountEntity userAccount = tradeFeign.selectAccountByUid(userTradeHistoryEntity.getPurchaseUid());

                        TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(orderId);
                        if (null != tradePoundageEntity) {
                            // 计算手续费
                            // 手续费=卖出数量*0.05
                            UserBrokerageEntity userBrokerageEntity = tradeFeign.selectUserBrokerageById(1);
                            if (null != userBrokerageEntity) {
                                BigDecimal multiplyB = userBrokerageEntity.getSellBrokerage().add(tradePoundageEntity.getPoundage());
                                userBrokerageEntity.setSellBrokerage(multiplyB);
                                tradeFeign.updateUserBrokerage(userBrokerageEntity);

                                BigDecimal regulateIncome = userAccount.getRegulateIncome().add(quantity);
                                userAccount.setRegulateIncome(regulateIncome);
                                BigDecimal availableAssets = userAccount.getAvailableAssets().add(quantity);
                                userAccount.setAvailableAssets(availableAssets);
                                userAccount.setUid(userTradeHistoryEntity.getPurchaseUid());
                                tradeFeign.updateUserAccount(userAccount);

                                //删除手续费记录
                              //  param.put("orderId", orderId);
                                tradeFeign.deleteTradePoundageById(orderId);

                                // 添加购买记录
                                UserTradeHistoryEntity userTradeHistory = new UserTradeHistoryEntity();
                                userTradeHistory.setUserTradeId(userTradeBean.getId());
                                userTradeHistory.setSellUid(uid);
                                userTradeHistory.setCreateTime(new Date());
                                userTradeHistory.setSellQuantity(quantity);
                                userTradeHistory.setState(Common.STATE_PAY);
                                userTradeHistory.setPrice(userTradeBean.getPrice());
                                userTradeHistory.setFinishTime(new Date());
                                tradeFeign.updateUserTradeHistory(userTradeHistory);
                            }
                        } else {
                            logger.info("");
                            return R.error();
                        }
                    }
                } else {
                    R.error("订单不存在！");
                }
            }
            return R.ok();
        }
        return R.error("交易密码错误");

    }


}
