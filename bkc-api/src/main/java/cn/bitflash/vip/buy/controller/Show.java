package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserBuyMessageBean;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.ShowFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy/list")
public class Show {

    @Autowired
    private ShowFeign feign;

    /**-----------------------------------------------显示求购信息列表-----------------------------------------------------*/

    /**
     * ----------------交易页-----------------
     *
     * @param pages 分页
     * @return 除用户所有求购信息
     */
    @Login
    @PostMapping("showBuyMessage")
    public R showNeedMessage(@RequestAttribute("uid") String uid, @RequestParam("pages") String pages) {
        List<UserBuyMessageBean> ub = feign.getBuyMessage(uid, Integer.valueOf(pages));
        if (ub == null || ub.size() < 0) {
            return R.error("暂时没有求购信息");
        }
        Integer count = feign.getNumToPaging();
        return R.ok().put("count", count).put("list", ub);
    }

    /**
     * ---------------订单页----------------
     *
     * @param pages 分页
     * @return 用户的所有交易信息
     */
    @Login
    @PostMapping("showBuyMessageOwn")
    public R showUserBuyMessage(@RequestAttribute("uid") String uid, @RequestParam("pages") String pages) {

        List<UserBuyBean> userBuyEntities = feign.selectBuyList(uid, Integer.valueOf(pages));
        List<UserBuyBean> userBuyEntitiesList = new LinkedList<UserBuyBean>();
        String state = null;

        Integer count = feign.selectUserBuyOwnCount(uid);

        for (UserBuyBean userBuyEntity : userBuyEntities) {
            if (userBuyEntity.getUid().equals(uid)) {
                state = userBuyEntity.getState();
            } else if (uid.equals(userBuyEntity.getSellUid())) {
                state = userBuyEntity.getSellState();
            } else if (uid.equals(userBuyEntity.getPurchaseUid())) {
                state = userBuyEntity.getPurchaseState();
            }

            if (STATE_BUY_CANCEL.equals(state)) {
                state = "可撤销";
            } else if (STATE_BUY_ACCMONEY.equals(state)) {
                state = "待收款";
            } else if (STATE_BUY_PAYMONEY.equals(state)) {
                state = "待付款";
            } else if (STATE_BUY_PAYCOIN.equals(state)) {
                state = "待确认";
            } else if (STATE_BUY_ACCCOIN.equals(state)) {
                state = "待收币";
            }
            userBuyEntity.setState(state);
            userBuyEntitiesList.add(userBuyEntity);
        }
        return R.ok().put("userBuyEntitiesList", userBuyEntitiesList).put("count", count);
    }
}
