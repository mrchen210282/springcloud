package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserTradeEntity;
import cn.bitflash.entity.UserTradeHistoryEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.trade.feign.TradeFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class Appeal {

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("complaint")
    @Transactional
    public R complaint(@RequestParam String uid, @RequestParam String orderId, @RequestParam String complaintState) {
        if(StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(orderId) && StringUtils.isNotBlank(complaintState)) {
            UserComplaintEntity userComplaintEntity = tradeFeign.selectUserCompById(orderId);
            if (null != userComplaintEntity) {
                return R.error().put("code","500");
            }

            //设置交易订单状态为9申拆中
            UserTradeEntity userTradeEntity = new UserTradeEntity();
            userTradeEntity.setId(orderId);
            //申拆中
            userTradeEntity.setState(Common.STATE_COMPLAINT);
            tradeFeign.insertOrUpdateTrade(userTradeEntity);

            //查询卖入订单信息

            UserTradeHistoryEntity userTradeHistoryEntity =  tradeFeign.selectTradeHistoryById(orderId);

            if(null != userTradeHistoryEntity) {
                //添加申拆
                userComplaintEntity = new UserComplaintEntity();
                userComplaintEntity.setComplaintState(complaintState);

                userComplaintEntity.setComplaintUid(userTradeHistoryEntity.getSellUid());
                //订单发布人
                userComplaintEntity.setContactsUid(userTradeHistoryEntity.getPurchaseUid());
                userComplaintEntity.setOrderId(orderId);
                userComplaintEntity.setOrderState(Common.COMPLAINT_NO);
                userComplaintEntity.setCreateTime(new Date());
                tradeFeign.insertUserComplaint(userComplaintEntity);
            } else {
                //申拆订单不存在
                return R.error().put("code","501");
            }
        }
        return R.ok();
    }

}
