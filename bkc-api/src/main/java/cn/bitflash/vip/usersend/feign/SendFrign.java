package cn.bitflash.vip.usersend.feign;

import cn.bitflash.entities.UserBrokerageEntity;
import cn.bitflash.entities.UserEntity;
import cn.bitflash.entities.UserPayPwdEntity;
import cn.bitflash.entities.UserSendEntity;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
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
    @PostMapping("/inner/user/selectByUuid")
    UserEntity selectUserByUuid(@RequestParam("uuid") String uuid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("id") String uid);

    /**
     * user_trade_config 表
     */
    @PostMapping("/inner/userTradeConfig/selectById")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_brokerage表
     */
    @PostMapping("/inner/userBrokerage/selectById")
    UserBrokerageEntity selectUserBrokerage(@RequestParam("id") int id);

    @PostMapping("/inner/userBrokerage/updateById")
    Boolean updateUserBrokerageById(@RequestBody UserBrokerageEntity brokerage);


    /**
     * user_account表
     */
    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userAccount/updateById")
    Boolean updateAccountById(@RequestBody UserAccountEntity account);

    /**
     * user_send 表
     */
    @PostMapping("/inner/userSend/insert")
    Boolean insertUserSend(@RequestBody UserSendEntity send);

    @PostMapping("/inner/userSend/selectAccount")
    List<UserSendEntity> selectAccountByPages(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    @PostMapping("/inner/userSend/selectaccept")
    List<UserSendEntity> selectaccept(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    @PostMapping("/inner/userSend/selectaccountcount")
    Integer selectaccountcount(@RequestParam("uid") String uid);
}
