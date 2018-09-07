package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class remind {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * ------------------4--------------------
     * <p>
     * -------------点击催单(待收款/待收币)------------
     */
    @PostMapping("remind")
    @Transactional(propagation = Propagation.REQUIRED)
    public R reminders(@RequestParam("id") String id) {
        UserBuyHistoryEntity userBuyHistoryEntity = userBuyHistoryService.selectOne(new EntityWrapper<UserBuyHistoryEntity>().eq("user_buy_id", id));
        //获取Cid
        String cid = null;
        //获取推送信息
        String text = null;
        if (STATE_BUY_PAYMONEY.equals(userBuyHistoryEntity.getPurchaseState())) {
            cid = loginUtils.selectGT(new ModelMap("uid", userBuyHistoryEntity.getPurchaseUid())).getCid();
            text = sysUtils.getVal("paymoney");
        }
        if (STATE_BUY_ACCCOIN.equals(userBuyHistoryEntity.getPurchaseState())) {
            cid = loginUtils.selectGT(new ModelMap("uid", userBuyHistoryEntity.getSellUid())).getCid();
            text = sysUtils.getVal("reminders");
        }
        String idVal = redisUtils.get(Common.ADD_LOCKNUM + id);
        if (StringUtils.isBlank(idVal)) {
            try {
                GeTuiSendMessage.sendSingleMessage(text, cid);
                redisUtils.set(Common.ADD_LOCKNUM + id, id, 60 * 60);
            } catch (Exception e) {
                return R.error("推送失败");
            }
        }
        return R.ok().put("code", SUCCESS);
    }

    /**
     * ------------------4.1--------------------
     * <p>
     * -------------判定是否催单(待收币)------------
     */
    @PostMapping("remind/decide")
    public R checkReminders(@RequestParam("id") String id) {
        if (redisUtils.get(Common.ADD_LOCKNUM + id) == null || "".equals(redisUtils.get(Common.ADD_LOCKNUM + id))) {
            return R.ok().put("state", "0");
        } else {
            return R.ok().put("state", "1");
        }
    }
}
