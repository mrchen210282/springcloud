package cn.bitflash.service.impl;


import cn.bitflash.dao.UserTradeHistoryDao;
import cn.bitflash.entities.UserTradeHistoryEntity;
import cn.bitflash.entity.UserTradeHistoryBean;
import cn.bitflash.service.UserTradeHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangjun
 * @date 2018年5月21日 下午4:48:48
 */
@Service("userTradeHistoryService")
public class UserTradeHistoryServiceImpl extends ServiceImpl<UserTradeHistoryDao, UserTradeHistoryEntity> implements UserTradeHistoryService {
    @Override
    public void updateUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory) {
        baseMapper.updateUserTradeHistory(uesrTradeHistory);
    }
    @Override
    public List<UserTradeHistoryBean> selectTradeHistory(Map<String, Object> map) {
        List<UserTradeHistoryBean> list = baseMapper.selectTradeHistory(map);
        return list;
    }
    @Override
    public Map<String, Object> selectTradeHistoryIncome(Map<String, Object> map) {
        Map<String, Object> returnMap = baseMapper.selectTradeHistoryIncome(map);
        return returnMap;
    }
    @Override
    public void insertUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory) {
        baseMapper.insertUserTradeHistory(uesrTradeHistory);
    }

}
