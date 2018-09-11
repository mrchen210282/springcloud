package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

public interface AppealFeign {

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectById(String id);

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(String id);

    @PostMapping("/inner/userBuy/updateById")
    void updateById(UserBuyEntity entity);

    @PostMapping("/inner/userComplaint/insert")
    void insert(UserComplaintEntity entity);
}
