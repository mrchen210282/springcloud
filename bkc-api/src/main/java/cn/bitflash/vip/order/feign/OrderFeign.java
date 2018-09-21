package cn.bitflash.vip.order.feign;

import cn.bitflash.entities.UserBuyEntity;
import cn.bitflash.entities.UserDigitalIncome;
import cn.bitflash.entities.UserTradeEntity;
import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserComplaintBean;
import cn.bitflash.entity.UserTradeJoinBuyEntity;
import cn.bitflash.vip.order.entity.OrderTradeDetail;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface OrderFeign {

    @PostMapping("")
    List<UserBuyBean> selectAppealList(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    @PostMapping("")
    Integer selectAppealCount(@RequestParam("uid")String uid);
    @PostMapping("")
    UserComplaintBean getComplaintMessage(@RequestParam("id")String id);

    /**
     * user_account表
     */
    @PostMapping("")
    UserDigitalIncome selectAccountByUid(@RequestParam("uid")String uid);

    /**
     * user_trade 表
     */
    @PostMapping("")
    UserTradeEntity selectTradeById(@RequestParam("id")String id);

    /**
     * user_buy 表
     */
    @PostMapping("")
    UserBuyEntity selectUserBuyById(@RequestParam("id")String id);

    /**
     * user_trade_config 表
     */
    @PostMapping("")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_trade_history
     */
    List<UserTradeJoinBuyEntity> selectFinishOrder(@RequestParam("uid")String uid,
                                                   @RequestParam("pageNum")String pageNum,
                                                   @RequestParam("pageTotal")String pageTotal);

    Integer selectFinishOrderCount(@RequestParam("uid")String uid,
                                   @RequestParam("pageNum")String pageNum,
                                   @RequestParam("pageTotal")String pageTotal);

    OrderTradeDetail checkSuccess(@RequestParam("id")String id);
}
