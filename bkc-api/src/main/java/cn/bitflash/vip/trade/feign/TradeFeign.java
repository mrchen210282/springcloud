package cn.bitflash.vip.trade.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import cn.bitflash.vip.trade.entity.UserTradeDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "bkc-model")
public interface TradeFeign {

    /**
     * user_complaint 表
     */
    @PostMapping("")
    UserComplaintEntity selectUserCompById(@RequestParam("orderId") String orderId);

    @PostMapping("")
    Boolean insertUserComplaint(@RequestBody UserComplaintEntity complaint);

    /**
     * user_trade 表
     */
    @PostMapping("")
    Boolean insertOrUpdateTrade(@RequestBody UserTradeEntity trade);

    @PostMapping("")
    Boolean updateTrade(@RequestBody UserTradeEntity trade);

    @PostMapping("")
    Boolean deleteTrade(@RequestParam("id") String id);

    @PostMapping("")
    UserTradeEntity selectTradeById(@RequestParam("id") String id);

    @PostMapping("")
    UserTradeEntity selectTradeByIdAndState(@RequestParam("id") String id,@RequestParam("state") String state);

    @PostMapping("")
    UserTradeDetail selectDetail(@RequestParam("id") String id);

    @PostMapping("")
    AllUserTradeDetail queryDetail(@RequestParam("id") String id);

    @PostMapping("")
    List<TradeListEntity> selectTradeHistory(@RequestBody Map<String, Object> param);

    @PostMapping("")
    List<TradeListEntity> tradeList(@RequestParam("uid")String uid,@RequestParam("pageNum")String pageNum,@RequestParam("pageTotal")String pageTotal);

    @PostMapping("")
    List<UserTradeEntity> selectTradeByState(@RequestParam("state") String state);

    @PostMapping("")
    List<OrderListEntity> selectOrderTrade(@RequestParam("uid")String uid,@RequestParam("pageNum")String pageNum,@RequestParam("pageTotal")String pageTotal);

    @PostMapping("")
    Integer selectTradeCount(@RequestParam("uid")String uid,@RequestParam("pageNum")String pageNum,@RequestParam("pageTotal")String pageTotal);

    @PostMapping("")
    Integer tradeListCount(@RequestParam("uid")String uid,@RequestParam("pageNum")String pageNum,@RequestParam("pageTotal")String pageTotal);

    /**
     * user_trade_history 表
     */
    @PostMapping("")
    UserTradeHistoryEntity selectTradeHistoryById(@RequestParam("orderId") String orderId);

    @PostMapping("")
    Boolean updateUserTradeHistory(@RequestBody UserTradeHistoryEntity history);

    @PostMapping("")
    Boolean delUserTradeById(@RequestParam("orderId") String orderId);

    @PostMapping("")
    Boolean insertUserTradeHistory(@RequestBody UserTradeHistoryEntity historyEntity);

    /**
     * user_trade_config 表
     */
    @PostMapping("")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") Integer id);

    /**
     * trade_poundage 表
     */
    @PostMapping("")
    TradePoundageEntity selectTradePoundageById(@RequestParam("id") String id);

    @PostMapping("")
    Boolean deleteTradePoundageById(@RequestParam("id") String id);

    @PostMapping("")
    Boolean insertTradePoundage(@RequestBody TradePoundageEntity poundageEntity);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid") String uid);

    /**
     * user_account 表
     */
    @PostMapping("")
    UserAccountEntity selectAccountByUid(@RequestParam("uid") String uid);

    @PostMapping("")
    Boolean updateUserAccount(@RequestBody UserAccountEntity account);

    @PostMapping("")
    Map<String, Object> responseTrade(@RequestParam("uid") String uid);

    /**
     * user_brokerage 表
     */
    @PostMapping("")
    UserBrokerageEntity selectUserBrokerageById(@RequestParam("id") Integer id);

    @PostMapping("")
    Boolean updateUserBrokerage(@RequestBody UserBrokerageEntity brokerage);

    /**
     * user_getui_cid 表
     */
    @PostMapping("")
    UserGTCidEntity selectGT(@RequestParam("uid") String uid);

    /**
     * platform_config
     */
    @PostMapping("")
    String getVal(@RequestParam("key") String key);


}
