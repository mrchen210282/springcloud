package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserAccountEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface UserAccountFeign {

    @PostMapping("/inner/userAccount/selectOne")
    UserAccountEntity selectOne(Map<String, Object> param);

    @PostMapping("/inner/userAccount/updateById")
    void updateById(UserAccountEntity entity);

}
