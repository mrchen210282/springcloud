package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.entity.UserGTCidEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "bkc-model")
public interface RemindFeign {

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(@RequestParam("id")String id);

    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key")String key);

    @PostMapping("/inner/userGTCidEntity/selectOne")
    UserGTCidEntity selectOne(Map<String, Object> param);

}
