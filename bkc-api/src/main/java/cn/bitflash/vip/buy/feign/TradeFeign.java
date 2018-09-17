package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "bkc-model")
public interface TradeFeign {

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(@RequestParam("id") String id);

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(@RequestParam("id")String id);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(@RequestParam("id")String id);

    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(@RequestBody UserAccountEntity entity);

    @PostMapping("/inner/userTradeConfigEntity/selectOne")
    UserTradeConfigEntity selectOne(@RequestBody Map<String, Object> param);

}
