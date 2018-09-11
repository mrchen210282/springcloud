package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

public interface PendingPayFeign {

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(String id);

    @PostMapping("/inner/userBuy/updateById")
    void updateById(UserBuyEntity entity);

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(String id);

    @PostMapping("/inner/userBuyHistory/updateById")
    void updateHistoryById(UserBuyHistoryEntity entity);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(String id);

    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(UserAccountEntity entity);

    @PostMapping("/inner/tradePoundage/deleteById")
    void deletePoundageById(String id);

    @PostMapping("/inner/userBuy/deleteById")
    void deleteBuyById(String id);

    @PostMapping("/inner/userBuyHistory/deleteById")
    void deleteHistoryById(String id);

    @PostMapping("/inner/tradePoundage/selectById")
    TradePoundageEntity selectPoundageById(String id);


}
