package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface AddOrCancelFeign {

    @PostMapping("/inner/usreBuy/insert")
    void insertBuy(@RequestBody UserBuyEntity entity);

    @PostMapping("/inner/usreBuy/selectById")
    UserBuyEntity selectUsreBuyById(@RequestParam("id") String id);

    @PostMapping("/inner/usreBuy/updateById")
    void updateById(@RequestBody UserBuyEntity entity);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(@RequestParam("id") String id);

    @PostMapping("/inner/userBuyHistory/insert")
    void insertBuyHistory(@RequestBody UserBuyHistoryEntity entity);

    @PostMapping("/inner/tradePoundage/insert")
    void insertRTradePoundage(@RequestBody TradePoundageEntity entity);
}
