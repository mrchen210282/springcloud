package cn.bitflash.service.impl;

import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyMessageBean;
import cn.bitflash.dao.UserBuyDao;
import cn.bitflash.service.UserBuyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


/**
 * 求购
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
@Service("userBuyService")
public class UserBuyServiceImpl extends ServiceImpl<UserBuyDao, UserBuyEntity> implements UserBuyService {

    private final Logger logger = LoggerFactory.getLogger(UserBuyServiceImpl.class);

    @Override
    public Integer getNumToPaging() {
        return baseMapper.getNumToPaging();
    }

    /**
     * 交易页集合
     * @param uid
     * @param pages
     * @return
     */
    @Override
    public List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages){
        return baseMapper.showBuying(uid,pages);
    }

    /**
     * 订单页集合
     * @param uid
     * @param pages
     * @return
     */
    @Override
    public List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages){
        return  baseMapper.showOrder(uid,pages);
    }

    /**
     * 订单数量
     * @param uid
     * @return
     */
    @Override
    public Integer showOrderCount(@RequestParam("uid") String uid){
        return baseMapper.showOrderCount(uid);
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @Override
    public UserBuyBean checkOrder(@RequestParam("id")String id){
        return  baseMapper.checkOrder(id);
    }
}
