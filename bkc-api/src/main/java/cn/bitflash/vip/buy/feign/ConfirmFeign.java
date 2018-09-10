package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

public interface ConfirmFeign {

    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(String id);

    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(String id);

    @PostMapping("/inner/userBuy/updateById")
    void updateById(UserBuyEntity entity);

    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(String id);

    @PostMapping("/inner/userBuyHistory/updateById")
    void updateHistoryById(UserBuyHistoryEntity entity);

    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(UserAccountEntity entity);

    @PostMapping("inner/userPayPwd/selectUid")
    UserPayPwdEntity selectUid(String uid);

    @PostMapping("/inner/userBrokerage/updateById")
    void updateBrokerageById(UserBrokerageEntity entity);

    @PostMapping("/inner/userBrokerage/selectOne")
    UserBrokerageEntity selectBrokerageById(int id);

    @PostMapping("/inner/tradePoundage/deleteById")
    void deletePoundageById(String id);

}
