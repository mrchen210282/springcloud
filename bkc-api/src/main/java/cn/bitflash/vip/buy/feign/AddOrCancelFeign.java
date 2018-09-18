package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import cn.bitflash.exception.RRException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

public interface AddOrCancelFeign {

    @PostMapping("/inner/usreBuy/insert")
    void insertBuy(UserBuyEntity entity);

    @PostMapping("/inner/usreBuy/selectById")
    UserBuyEntity selectUsreBuyById(String id);

    @PostMapping("/inner/usreBuy/updateById")
    void updateById(UserBuyEntity entity);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(String id);

    @PostMapping("/inner/userBuyHistory/insert")
    void insertBuyHistory(UserBuyHistoryEntity entity);

    @PostMapping("/inner/buyPoundage/insert")
    void insertBuyPoundage(BuyPoundageEntity entity);

    @PostMapping("/inner/userBuy/deleteById")
    void deleteBuyById(String id);
}
