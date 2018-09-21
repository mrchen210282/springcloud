package cn.bitflash.vip.buy.controller;

import cn.bitflash.entities.UserBuyEntity;
import cn.bitflash.entities.UserComplaintEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.bitflash.util.Common.ORDER_STATE_APPEAL;
import static cn.bitflash.util.Common.SUCCESS;

@RestController
@RequestMapping("/buy")
public class Appeal {

    @Autowired
    private BuyFeign feign;

    /**
     * --------------点击申诉(待收币)---------
     */
    @PostMapping("appeal")
    @Transactional(propagation = Propagation.REQUIRED)
    public R appeal(@RequestParam("id") String id) {
        //修改订单状态
        UserBuyEntity userBuyEntity = feign.selectBuyById(id);
        userBuyEntity.setState(ORDER_STATE_APPEAL);
        feign.updateBuyById(userBuyEntity);
        //添加订单到申诉表中
        UserComplaintEntity userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState("1");
        userComplaintEntity.setComplaintUid(userBuyEntity.getPurchaseUid());
        userComplaintEntity.setContactsUid(userBuyEntity.getSellUid());
        userComplaintEntity.setCreateTime(new Date());
        userComplaintEntity.setOrderId(id);
        userComplaintEntity.setOrderState(userBuyEntity.getState());
        feign.insertComplaint(userComplaintEntity);
        return R.ok().put("code", SUCCESS);
    }
}
