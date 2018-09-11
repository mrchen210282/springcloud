package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserFeign {

    /**
     * user_account表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    /**
     * user_info表
     */
    //返回对应列的值
    String selectUserInfoById(@RequestParam("uid")String uid,String column);

    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    /**
     * user_trade_hostory表
     */
    //finsh_time !=null
    List<UserTradeHistoryEntity> selectTradeHistoryList(@RequestParam("uid")String uid);

    /**
     * user_buy_hostory表
     */
    List<UserBuyHistoryEntity> selectBuyHistoryList(@RequestParam("uid")String uid);

    /**
     * user_pay_url表
     */
    UserPayUrlEntity selectUserPayUrlByUidAndType(@RequestParam("uid")String uid, @RequestParam("type")String type);

    Boolean insertUserUrl(@RequestBody UserPayUrlEntity userPayUrlEntity);

    Boolean updateUserUrlById(@RequestBody UserPayUrlEntity userPayUrlEntity);
}
