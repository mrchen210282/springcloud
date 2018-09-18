package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface ShowFeign {
    @PostMapping("/inner/userBuy/getBuyMessage")
    List<UserBuyMessageBean> getBuyMessage(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);

    @PostMapping("/inner/userBuy/getNumToPaging")
    Integer getNumToPaging();

    @PostMapping("/inner/userBuy/selectBuyList")
    List<UserBuyBean> selectBuyList(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);

    @PostMapping("/inner/userBuy/selectUserBuyOwnCount")
    Integer selectUserBuyOwnCount(@RequestParam("uid") String uid);
}
