//package cn.bitflash.vip.trade.controller;
//
//import cn.bitflash.annotation.Login;
//import cn.bitflash.entities.UserDigitalIncome;
//import cn.bitflash.entity.OrderListBean;
//import cn.bitflash.entity.OrderListEntity;
//import cn.bitflash.entity.TradeListBean;
//import cn.bitflash.entity.TradeListEntity;
//import cn.bitflash.entity.UserAccountEntity;
//import cn.bitflash.util.Assert;
//import cn.bitflash.util.BigDecimalUtils;
//import cn.bitflash.util.Common;
//import cn.bitflash.util.R;
//import cn.bitflash.vip.trade.feign.TradeFeign;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("trade")
//@Api(value = "交易列表页", tags = "显示")
//public class TradeList {
//
//    @Autowired
//    private TradeFeign tradeFeign;
//
//    @Login
//    @PostMapping("/tradeList")
//    @ApiOperation(value = "交易列表")
//    public R tradeList(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String pageNum) {
//        UserDigitalIncome userAccount = tradeFeign.selectAccountByUid(uid);
//        Assert.isNull(userAccount, "无此用户");
//        // 查询自身用户信息
//        List<TradeListBean> listEntity = tradeFeign.tradeList(uid, pageNum, "6");
//        Integer count = tradeFeign.tradeListCount(uid, pageNum, "6");
//        Map<String, Object> param = new HashMap<>();
//        param.put("availableAssets", BigDecimalUtils.DecimalFormat(userAccount.getAvailableAssets()));
//        param.put("userAccountList", listEntity);
//        param.put("totalRecord", count);
//
//        return R.ok().put("userAccount", param);
//    }
//
//    @Login
//    @PostMapping("/orderList")
//    @ApiOperation(value = "订单列表")
//    public R orderList(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String pageNum) {
//        UserDigitalIncome userAccount = tradeFeign.selectAccountByUid(uid);
//        int pageTotal = 6;
//        Map<String, Object> param = new HashMap<String, Object>();
//        if (StringUtils.isNotBlank(userAccount.getUid())) {
//            // 查询交易
//            List<OrderListBean> listEntity = tradeFeign.selectOrderTrade(uid, pageNum, "6");
//            Integer count = tradeFeign.selectTradeCount(uid, pageNum, "6");
//            param.put("availableAssets", Common.decimalFormat(Double.parseDouble(userAccount.getAvailableAssets().toString())));
//            param.put("userAccountList", listEntity);
//            param.put("totalRecord", count);
//        } else {
//            return R.error("无此用户！");
//        }
//        return R.ok().put("userAccount", param);
//    }
//
//
//}
