package cn.bitflash.vip.verify.feign;

import cn.bitflash.entities.UserInfoEntity;
import cn.bitflash.entities.UserPayImgEntity;
import cn.bitflash.entities.UserPayPwdEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface VerifyFeign {
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserInfoByUid(@RequestParam("id") String uid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("id") String uid);

    /**
     * user_pay_url表
     */
    @PostMapping("/inner/userPayUrl/selectList")
    List<UserPayImgEntity> selectUserUrlList(@RequestParam("id") String uid);
}
