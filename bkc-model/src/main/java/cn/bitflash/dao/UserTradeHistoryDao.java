package cn.bitflash.dao;

import cn.bitflash.entities.UserTradeHistoryEntity;
import cn.bitflash.entity.UserTradeHistoryBean;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author wangjun
 * @date 2018年6月19日 下午4:45:51
 */
public interface UserTradeHistoryDao extends BaseMapper<UserTradeHistoryEntity> {

    void updateUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory);

    List<UserTradeHistoryBean> selectTradeHistory(Map<String, Object> map);

    Map<String, Object> selectTradeHistoryIncome(Map<String, Object> map);

    void insertUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory);
}