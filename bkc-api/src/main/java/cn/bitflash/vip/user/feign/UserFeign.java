package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface UserFeign {

    /**
     * tb_user表
     */
    @PostMapping("")
    UserEntity selectUserByUid(@RequestParam("uid") String uid);

    @PostMapping("")
    Boolean updateUserById(@RequestBody UserEntity userEntity);

    /**
     * user_account表
     */
    @PostMapping("")
    UserAccountEntity selectAccountByUid(@RequestParam("uid") String uid);

    /**
     * user_info表
     */
    //返回对应列的值
    @PostMapping("")
    String selectUserInfoByColumn(@RequestParam("uid") String uid, @RequestParam("column") String column);

    @PostMapping("")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    @PostMapping("")
    UserInfoEntity selectUserinfoById(@RequestParam("uid") String uid);


    /**
     * user_trade_hostory表
     */
    //finsh_time !=null
    @PostMapping("")
    List<UserTradeHistoryEntity> selectTradeHistoryList(@RequestParam("uid") String uid);

    /**
     * user_buy_hostory表
     */
    @PostMapping("")
    List<UserBuyHistoryEntity> selectBuyHistoryList(@RequestParam("uid") String uid);

    /**
     * user_pay_url表
     */
    @PostMapping("")
    UserPayUrlEntity selectUserPayUrlByUidAndType(@RequestParam("uid") String uid, @RequestParam("type") String type);

    @PostMapping("")
    Boolean insertUserUrl(@RequestBody UserPayUrlEntity userPayUrlEntity);

    @PostMapping("")
    Boolean updateUserUrlById(@RequestBody UserPayUrlEntity userPayUrlEntity);

    //imgType 1,2,5
    @PostMapping("")
    List<UserPayUrlEntity> selectUserUrlList(@RequestParam("uid") String uid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid") String uid);

    @PostMapping("")
    Boolean insertUserPwd(@RequestBody UserPayPwdEntity userPayPwdEntity);

    @PostMapping("")
    Boolean updateUserPwdById(@RequestBody UserPayPwdEntity userPayPwdEntity);

    /**
     * user_trade 表
     */
    @PostMapping("")
    UserTradeEntity selectTradeById(@RequestParam("id") String id);

    /**
     * user_buy_history 表
     */
    @PostMapping("")
    UserBuyHistoryEntity selectBuyHistoryById(@RequestParam("id") String id);
}
