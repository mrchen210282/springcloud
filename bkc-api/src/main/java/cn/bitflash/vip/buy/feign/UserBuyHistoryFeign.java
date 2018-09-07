package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyHistoryBean;
import cn.bitflash.entity.UserBuyHistoryEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface UserBuyHistoryFeign {

    @PostMapping("/inner/userBuyHistory/insert")
    void insert(UserBuyHistoryEntity entity);

    @PostMapping("/inner/userBuyHistory/selectOne")
    UserBuyHistoryEntity selectOne(Map<String, Object> param);

    @PostMapping("/inner/userBuyHistory/selectBuyHistory")
    UserBuyHistoryBean selectBuyHistory(String id);

    @PostMapping("/inner/userBuyHistory/updateById")
    void updateById(UserBuyHistoryEntity entity);

    @PostMapping("/inner/userBuyHistory/deleteById")
    void deleteById(String id);
}
