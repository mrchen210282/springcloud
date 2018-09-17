package cn.bitflash.vip.verify.feign;

import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.entity.UserPayUrlEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface VerifyFeign {
    @PostMapping("")
    UserInfoEntity selectUserInfoByUid(@RequestParam("uid")String uid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid")String uid);

    /**
     * user_pay_url表
     */
    //imgType 1,2,5
    @PostMapping("")
    List<UserPayUrlEntity> selectUserUrlList(@RequestParam("uid")String uid);
}
