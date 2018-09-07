package cn.bitflash.vip.buy.controller;


import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.UserBuyFeign;
import cn.bitflash.vip.buy.feign.UserBuyHistoryFeign;
import cn.bitflash.vip.buy.feign.UserComplaintFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.bitflash.util.Common.*;

@RestController
@RequestMapping("/buy")
public class appeal {

    @Autowired
    private UserBuyFeign userBuyFeign;

    @Autowired
    private UserComplaintFeign userComplaintFeign;

    @Autowired
    private UserBuyHistoryFeign userBuyHistoryFeign;

    /**
     * --------------点击申诉(待收币)---------
     */
    @PostMapping("appeal")
    @Transactional(propagation = Propagation.REQUIRED)
    public R appeal(@RequestParam("id") String id) {
        UserBuyHistoryEntity userBuyHistoryEntity = userBuyHistoryFeign.selectOne(new ModelMap("user_buy_id", id));
        //修改订单状态
        UserBuyEntity userBuyEntity = userBuyFeign.selectOne(new ModelMap("id",id));
        userBuyEntity.setState(STATE_BUY_APPEAL);
        userBuyFeign.updateById(userBuyEntity);
        //添加订单到申诉表中
        UserComplaintEntity userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState("1");
        userComplaintEntity.setComplaintUid(userBuyEntity.getUid());
        userComplaintEntity.setContactsUid(userBuyHistoryEntity.getSellUid());
        userComplaintEntity.setCreateTime(new Date());
        userComplaintEntity.setOrderId(id);
        userComplaintEntity.setOrderState("0");
        userComplaintFeign.insert(userComplaintEntity);
        return R.ok().put("code", SUCCESS);
    }

}
