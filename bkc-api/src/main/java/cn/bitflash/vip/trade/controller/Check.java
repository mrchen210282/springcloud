package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.entity.UserTradeBean;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Check {

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("selectDetail")
    public R selectDetail(@RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            UserTradeBean userTradeBean = tradeFeign.selectDetail(id);

            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(id);



            if (null != tradePoundageEntity) {
                //扣除交易额=交易额+手续费
                BigDecimal deductAmount = userTradeBean.getQuantity().add(tradePoundageEntity.getPoundage());
                userTradeBean.setDeductAmount(deductAmount);
            }

            if (null != userTradeBean) {
                //数量
                BigDecimal quantity = userTradeBean.getQuantity();
                //价格
                BigDecimal price = userTradeBean.getPrice();
                BigDecimal tradeAmount = price.multiply(quantity);

                userTradeBean.setTradeAmount(tradeAmount);
            }
            return R.ok().put("userTradeBean", userTradeBean);
        } else {
            return R.error("参数不能为空！");
        }
    }

    @Login
    @PostMapping("viewDetail")
    public R viewDetail(@RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            UserTradeBean userTradeBean = tradeFeign.queryDetail(id);

            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(id);

            if (null != tradePoundageEntity) {
                //扣除交易额=交易额+手续费
                BigDecimal deductAmount = userTradeBean.getQuantity().add(tradePoundageEntity.getPoundage());
                userTradeBean.setDeductAmount(deductAmount);
            }

            if (null != userTradeBean) {
                //数量
                BigDecimal quantity = userTradeBean.getQuantity();
                //价格
                BigDecimal price = userTradeBean.getPrice();
                BigDecimal tradeAmount = price.multiply(quantity);

                userTradeBean.setTradeAmount(tradeAmount);
            }
            return R.ok().put("userTradeBean", userTradeBean);
        } else {
            return R.error("参数不能为空！");
        }
    }

    @Login
    @PostMapping("listTrade")
    public R listTrade(@RequestAttribute("uid") String uid, @RequestParam String state) {
        Map<String, Object> param = new HashMap<>();
        List<UserTradeBean> list = null;
        if (StringUtils.isNotBlank(state)) {
            param.put("uid", uid);
            if (state.equals("1")) {
                param.put("state", Common.STATE_PAY);
            } else {
                param.put("state", state);
            }
            list = tradeFeign.selectTradeHistory(param);
        }
        return R.ok().put("tradeHistoryList", list);
    }
}
