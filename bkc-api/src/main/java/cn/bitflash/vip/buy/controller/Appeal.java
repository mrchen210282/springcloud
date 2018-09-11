package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.AppealFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class Appeal {

    @Autowired
    private AppealFeign feign;

    /**
     * --------------点击申诉(待收币)---------
     */
    @PostMapping("appeal")
    @Transactional(propagation = Propagation.REQUIRED)
    public R appeal(@RequestParam("id") String id) {
        UserBuyHistoryEntity userBuyHistoryEntity = feign.selectById(id);
        //修改订单状态
        UserBuyEntity userBuyEntity = feign.selectBuyById(id);
        userBuyEntity.setState(STATE_BUY_APPEAL);
        feign.updateById(userBuyEntity);
        //添加订单到申诉表中
        UserComplaintEntity userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState("1");
        userComplaintEntity.setComplaintUid(userBuyEntity.getUid());
        userComplaintEntity.setContactsUid(userBuyHistoryEntity.getSellUid());
        userComplaintEntity.setCreateTime(new Date());
        userComplaintEntity.setOrderId(id);
        userComplaintEntity.setOrderState("0");
        feign.insert(userComplaintEntity);
        return R.ok().put("code", SUCCESS);
    }
}
