package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface CheckFeign {
    @PostMapping("/inner/usreBuy/selectById")
    UserBuyEntity selectUsreBuyById(@RequestParam("id") String id);

    @PostMapping("/inner/userBuyHistory/selectBuyHistory")
    UserBuyHistoryBean selectBuyHistory(@RequestParam("id") String id);
}
