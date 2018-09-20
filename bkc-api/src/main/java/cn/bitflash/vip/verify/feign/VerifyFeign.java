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
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserInfoByUid(@RequestParam("id")String uid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("id")String uid);

    /**
     * user_pay_url表
     */
    @PostMapping("/inner/userPayUrl/selectList")
    List<UserPayUrlEntity> selectUserUrlList(@RequestParam("id")String uid);
}
