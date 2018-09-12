package cn.bitflash.vip.verify.feign;

import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.entity.UserPayUrlEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VerifyFeign {

    UserInfoEntity selectUserInfoByUid(@RequestParam("uid")String uid);

    /**
     * user_pay_pwd 表
     */
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid")String uid);

    /**
     * user_pay_url表
     */
    //imgType 1,2,5
    List<UserPayUrlEntity> selectUserUrlList(@RequestParam("uid")String uid);
}
