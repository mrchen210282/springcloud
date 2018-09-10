package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface TradeFeign {

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(String id);

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(String id);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(String id);

    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(UserAccountEntity entity);

    @PostMapping("/inner/userTradeConfigEntity/selectOne")
    UserTradeConfigEntity selectOne(Map<String, Object> param);

}
