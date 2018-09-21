package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entities.UserGTCidEntity;
import cn.bitflash.util.*;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trade")
@Api(value = "催单", tags = "催单，验证催单次数")
public class RemindTrade {


    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TradeFeign tradeFeign;


    @Login
    @PostMapping("singleMsg")
    public R sendSingleMsg(@ApiParam(value = "被催单人的uid") @RequestParam String uid,
                           @ApiParam(value = "订单id") @RequestParam String id) throws Exception {
        String redisKey = RedisKey.SEND_TRADE_MESSAGE;
        String idVal = redisUtils.get(redisKey);
        Assert.isNotBlank(idVal, "501");
        try {
            UserGTCidEntity gtCidEntity = tradeFeign.selectGT(uid);
            String text = tradeFeign.getVal(Common.MSG_TEXT);
            GeTuiSendMessage.sendSingleMessage(text, gtCidEntity.getCid());
            redisUtils.set(redisKey, id, 60 * 60);
            return R.ok();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return R.error(502, "对方未登录最新App，无法催单");
        }
    }

    /*@Login
    @PostMapping("validateSendMessage")
    public R validateSendMessage(@RequestParam String id) throws Exception {
        String idVal = redisUtils.get(RedisKey.SEND_TRADE_MESSAGE + id);
        if (StringUtils.isBlank(idVal)) {
            return R.ok().put("state", "0");
        } else {
            return R.ok().put("state", "1");
        }
    }*/
}
