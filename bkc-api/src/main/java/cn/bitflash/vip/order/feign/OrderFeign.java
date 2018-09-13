package cn.bitflash.vip.order.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderFeign {

    List<UserBuyBean> selectAppealList(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    Integer selectAppealCount(@RequestParam("uid")String uid);

    UserComplaintBean getComplaintMessage(@RequestParam("id")String id);

    /**
     * user_account表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    /**
     * user_trade 表
     */
    UserTradeEntity selectTradeById(@RequestParam("id")String id);

    /**
     * user_buy 表
     */
    UserBuyEntity selectUserBuyById(@RequestParam("id")String id);

    /**
     * user_trade_config 表
     */
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);
}
