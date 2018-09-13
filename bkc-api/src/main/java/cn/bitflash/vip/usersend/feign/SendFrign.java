package cn.bitflash.vip.usersend.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SendFrign {


    /**
     * tb_user表
     */
    UserEntity selectUserByUuid(@RequestParam("uuid") String uuid);

    /**
     * user_pay_pwd 表
     */
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid") String uid);

    /**
     * user_trade_config 表
     */
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_brokerage表
     */
    UserBrokerageEntity selectUserBrokerage(@RequestParam("id") int id);

    Boolean updateUserBrokerageById(@RequestBody UserBrokerageEntity brokerage);


    /**
     * user_account表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    Boolean updateAccountById(@RequestBody UserAccountEntity account);

    /**
     * user_send 表
     */
    Boolean insertUserSend(@RequestBody UserSendEntity send);

    List<UserSendEntity> selectAccountByPages(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    List<UserSendEntity> selectaccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    Integer selectaccountcount(@RequestParam("uid")String uid);
}
