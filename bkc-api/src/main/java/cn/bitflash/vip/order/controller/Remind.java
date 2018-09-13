package cn.bitflash.vip.order.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.R;
import cn.bitflash.vip.order.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Remind {

    @Autowired
    private OrderFeign orderFeign;

    @Login
    @PostMapping("/appealList")
    public R selectAppealList(@RequestAttribute("uid") String uid, @RequestParam("pages") String pages) {

        List<UserBuyBean> ub = orderFeign.selectAppealList(uid, Integer.valueOf(pages));
        if (ub == null || ub.size() < 0) {
            return R.error("暂时没有申诉信息");
        }
        Integer count = orderFeign.selectAppealCount(uid);
        UserAccountEntity userAccount = orderFeign.selectAccountByUid(uid);
        return R.ok().put("count", count).put("list", ub).put("availableAssets", userAccount.getAvailableAssets());
    }

    @Login
    @PostMapping("/check")
    public cn.bitflash.utils.R checkAppeal(@RequestParam("id") String id, @RequestAttribute("uid") String uid) {
        String name = null;
        String mobile = null;

        UserComplaintBean userComplaintBean = orderFeign.getComplaintMessage(id);
        if (uid.equals(userComplaintBean.getComplaintUid())) {
            name = userComplaintBean.getContactsUname();
            mobile = userComplaintBean.getContactsMobile();

        } else if (uid.equals(userComplaintBean.getContactsUid())) {
            name = userComplaintBean.getComplaintUname();
            mobile = userComplaintBean.getComplaintMobile();
        }
        //判定订单不存在
        if (userComplaintBean == null) {
            return cn.bitflash.utils.R.ok().put("code", "订单不存在");
        }

        Map<String, Float> map = this.poundage(id, userComplaintBean.getComplaintState());

        return cn.bitflash.utils.R.ok().put("orderId", id).put("name", name).put("mobile", mobile).put("totalQuantity", map.get("totalQuantity")).put("price", map.get("price")).put("buyQuantity", map.get("buyQuantity")).put("totalMoney", map.get("totalMoney"));
    }

    public Map<String, Float> poundage(String id, String state) {

        DecimalFormat df = new DecimalFormat("#########.##");
        Float buyQuantity = 0f;
        Float price = 0f;
        if (state.equals("0")) {
            UserTradeEntity userTradeEntity = orderFeign.selectTradeById(id);
            price = Float.parseFloat(df.format(userTradeEntity.getPrice()));
            buyQuantity = Float.parseFloat(df.format(userTradeEntity.getQuantity()));
        } else if (state.equals("1")) {
            UserBuyEntity userBuy = orderFeign.selectUserBuyById(id);
            price = Float.parseFloat(df.format(userBuy.getPrice()));
            buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
        }

        //手续费比率
        Float poundage = orderFeign.selectTradeConfigById(1).getPoundage();
        //手续费数量
        Float totalPoundage = buyQuantity * poundage;
        //实际交易总数量
        Float totalQuantity = buyQuantity + totalPoundage;
        //总价格
        Float totalMoney = buyQuantity * (price);

        Map<String, Float> map = new HashMap<String, Float>();
        map.put("buyQuantity", buyQuantity);
        map.put("poundage", poundage);
        map.put("totalPoundage", totalPoundage);
        map.put("totalQuantity", totalQuantity);
        map.put("price", price);
        map.put("totalMoney", totalMoney);
        return map;
    }
}
