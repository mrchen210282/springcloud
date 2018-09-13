package cn.bitflash.vip.trade.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface TradeFeign {

    /**
     * user_complaint 表
     */
    UserComplaintEntity selectUserCompById(@RequestParam("orderId") String orderId);

    Boolean insertUserComplaint(@RequestBody UserComplaintEntity complaint);

    /**
     * user_trade 表
     */
    Boolean insertOrUpdateTrade(@RequestBody UserTradeEntity trade);

    Boolean updateTrade(@RequestBody UserTradeEntity trade);

    Boolean deleteTrade(@RequestParam("id")String id);

    UserTradeEntity selectTradeById(@RequestParam("id")String id);

    UserTradeBean selectDetail(@RequestParam("id")String id);

    UserTradeBean queryDetail(@RequestParam("id")String id);

    List<UserTradeBean> selectTradeHistory(@RequestBody Map<String, Object> param);

    List<UserTradeBean> tradeList(@RequestBody Map<String, Object> param);

    List<UserTradeEntity> selectTradeByState(@RequestParam("state")String state);

    List<UserTradeBean> selectOrderTrade(@RequestBody Map<String, Object> param);

    Integer selectTradeCount(@RequestBody Map<String, Object> param);

    Integer tradeListCount(@RequestBody Map<String, Object> param);
    /**
     * user_trade_history 表
     */
    UserTradeHistoryEntity selectTradeHistoryById(@RequestParam("orderId") String orderId);

    Boolean updateUserTradeHistory(@RequestBody UserTradeHistoryEntity history);

    Boolean delUserTradeById(@RequestParam("orderId")String orderId);

    /**
     * trade_poundage 表
     */
    TradePoundageEntity selectTradePoundageById(@RequestParam("id")String id);

    Boolean deleteTradePoundageById(@RequestParam("id")String id);

    /**
     * user_pay_pwd 表
     */
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid")String uid);

    /**
     * user_account 表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    Boolean updateUserAccount(@RequestBody UserAccountEntity account);

    /**
     * user_brokerage 表
     */
    UserBrokerageEntity selectUserBrokerageById(@RequestParam("id")Integer id);

    Boolean updateUserBrokerage(@RequestBody UserBrokerageEntity brokerage);

    /**
     * user_getui_cid 表
     */
    UserGTCidEntity selectGT(@RequestParam("uid")String uid);

    /**
     * platform_config
     */
    String getVal(@RequestParam("key")String key);





}
