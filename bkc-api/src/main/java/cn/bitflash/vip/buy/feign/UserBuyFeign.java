package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface UserBuyFeign {

    @PostMapping("/inner/usreBuy/insert")
    void insert(UserBuyEntity userBuyEntity);

    @PostMapping("/inner/usreBuy/selectOne")
    UserBuyEntity selectOne(Map<String, Object> param);

    @PostMapping("/inner/usreBuy/updateById")
    void updateById(UserBuyEntity entity);

    @PostMapping("/inner/usreBuy/deleteById")
    void deleteById(String id);

}
