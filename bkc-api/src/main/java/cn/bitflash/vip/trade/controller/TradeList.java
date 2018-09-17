package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserTradeBean;
import cn.bitflash.util.BigDecimalUtils;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeList {

    @Autowired
    private TradeFeign tradeFeign;


    @Login
    @PostMapping("/tradeList")
    public R tradeList(@RequestAttribute("uid")String uid, @RequestParam String pageNum) {
        UserAccountEntity userAccount = tradeFeign.selectAccountByUid(uid);
        int pageTotal = 6;
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(userAccount.getUid())) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", userAccount.getUid());
            map.put("pageNum", new Integer(pageNum));
            map.put("pageTotal", new Integer(pageTotal));
            // 查询自身用户信息
            List<UserTradeBean> listEntity = tradeFeign.tradeList(map);

            Integer count = tradeFeign.tradeListCount(map);

            param.put("availableAssets", BigDecimalUtils.DecimalFormat(userAccount.getAvailableAssets()));
            param.put("userAccountList", listEntity);
            param.put("totalRecord", count);
        } else {
            return R.error("无此用户！");
        }
        return R.ok().put("userAccount", param);
    }

    @Login
    @PostMapping("/orderList")
    public R orderList(@RequestAttribute("uid")String uid, @RequestParam String pageNum) {
        UserAccountEntity userAccount = tradeFeign.selectAccountByUid(uid);
        int pageTotal = 6;
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(userAccount.getUid())) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", userAccount.getUid());
            map.put("pageNum", new Integer(pageNum));
            map.put("pageTotal", new Integer(pageTotal));
            // 查询交易
            List<UserTradeBean> listEntity = tradeFeign.selectOrderTrade(map);

            Integer count = tradeFeign.selectTradeCount(map);

            param.put("availableAssets", Common.decimalFormat(Double.parseDouble(userAccount.getAvailableAssets().toString())));
            param.put("userAccountList", listEntity);
            param.put("totalRecord", count);
        } else {
            return R.error("无此用户！");
        }
        return R.ok().put("userAccount", param);
    }


}
