package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.GeTuiSendMessage;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public class Remind {


    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TradeFeign tradeFeign;


    @Login
    @PostMapping("singleMsg" )
    public R sendSingleMsg(@RequestParam String uid, @RequestParam String id) throws Exception {
        String idVal = redisUtils.get(Common.ADD_LOCK+id);
        if (StringUtils.isBlank(idVal)) {
            try {
                UserGTCidEntity gtCidEntity = tradeFeign.selectGT(uid);
                String text = tradeFeign.getVal(Common.MSG_TEXT);
                GeTuiSendMessage.sendSingleMessage(text, gtCidEntity.getCid());
                redisUtils.set(Common.ADD_LOCK+id, id, 60 * 60);
                return R.ok();
            } catch (NullPointerException e) {
                e.printStackTrace();
                return R.error(502, "对方未登录最新App，无法催单" );
            }

        }
        return R.error(501, "未超时" );
    }

    @Login
    @PostMapping("validateSendMessage" )
    public R validateSendMessage(@RequestParam String id) throws Exception {
        String idVal = redisUtils.get(Common.ADD_LOCK+id);
        if (StringUtils.isBlank(idVal)) {
            return R.ok().put("state", "0");
        } else {
            return R.ok().put("state", "1");
        }
    }
}
