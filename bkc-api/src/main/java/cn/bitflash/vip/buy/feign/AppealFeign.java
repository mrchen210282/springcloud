package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface AppealFeign {

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectById(@RequestParam("id") String id);

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(@RequestParam("id") String id);

    @PostMapping("/inner/userBuy/updateById")
    void updateById(@RequestBody UserBuyEntity entity);

    @PostMapping("/inner/userComplaint/insert")
    void insert(@RequestBody UserComplaintEntity entity);
}
