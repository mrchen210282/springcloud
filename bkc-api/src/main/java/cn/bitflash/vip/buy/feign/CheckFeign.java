package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryBean;
import org.springframework.web.bind.annotation.PostMapping;

public interface CheckFeign {
    @PostMapping("/inner/usreBuy/selectById")
    UserBuyEntity selectUsreBuyById(String id);

    @PostMapping("/inner/userBuyHistory/selectBuyHistory")
    UserBuyHistoryBean selectBuyHistory(String id);
}
