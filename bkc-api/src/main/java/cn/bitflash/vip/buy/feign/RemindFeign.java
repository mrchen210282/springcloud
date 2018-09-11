package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.entity.UserGTCidEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface RemindFeign {

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(String id);

    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(String key);

    @PostMapping("/inner/userGTCidEntity/selectOne")
    UserGTCidEntity selectOne(Map<String, Object> param);

}
