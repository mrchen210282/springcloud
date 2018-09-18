package cn.bitflash.vip.usersend.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface SendFrign {


    /**
     * tb_user表
     */
    @PostMapping("")
    UserEntity selectUserByUuid(@RequestParam("uuid") String uuid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid") String uid);

    /**
     * user_trade_config 表
     */
    @PostMapping("")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_brokerage表
     */
    @PostMapping("")
    UserBrokerageEntity selectUserBrokerage(@RequestParam("id") int id);
    @PostMapping("")
    Boolean updateUserBrokerageById(@RequestBody UserBrokerageEntity brokerage);


    /**
     * user_account表
     */
    @PostMapping("")
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);
    @PostMapping("")
    Boolean updateAccountById(@RequestBody UserAccountEntity account);

    /**
     * user_send 表
     */
    @PostMapping("")
    Boolean insertUserSend(@RequestBody UserSendEntity send);
    @PostMapping("")
    List<UserSendEntity> selectAccountByPages(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);
    @PostMapping("")
    List<UserSendEntity> selectaccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);
    @PostMapping("")
    Integer selectaccountcount(@RequestParam("uid")String uid);
}
