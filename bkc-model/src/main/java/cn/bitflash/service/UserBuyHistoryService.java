package cn.bitflash.service;


import cn.bitflash.entity.UserBuyHistoryBean;
import cn.bitflash.entity.UserBuyHistoryEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * 求购记录
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
public interface UserBuyHistoryService extends IService<UserBuyHistoryEntity> {

    /**
     * 查询订单详情
     *
     * @param id 订单id
     * @return
     */
    UserBuyHistoryBean selectBuyHistory(String id);
}
