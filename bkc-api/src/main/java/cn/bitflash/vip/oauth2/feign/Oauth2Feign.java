package cn.bitflash.vip.oauth2.feign;

import cn.bitflash.entity.UserAccountEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "bkc-model")
public interface Oauth2Feign {

    /**
     * user_account 表
     */
    @PostMapping("")
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);
    @PostMapping("")
    Boolean updateAccountById(@RequestBody UserAccountEntity account);

}
