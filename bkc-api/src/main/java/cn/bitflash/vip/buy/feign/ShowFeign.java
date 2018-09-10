package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ShowFeign {
    @PostMapping("/inner/userBuy/getBuyMessage")
    List<UserBuyMessageBean> getBuyMessage(String uid, Integer pages);

    @PostMapping("/inner/userBuy/getNumToPaging")
    Integer getNumToPaging();

    @PostMapping("/inner/userBuy/selectBuyList")
    List<UserBuyBean> selectBuyList(String uid, Integer pages);

    @PostMapping("/inner/userBuy/selectUserBuyOwnCount")
    Integer selectUserBuyOwnCount(String uid);
}
