package cn.bitflash.dao;

import cn.bitflash.entity.UserTradeBean;
import cn.bitflash.entity.UserTradeEntity;
import cn.bitflash.entity.UserTradeJoinBuyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangjun
 * @date 2018年6月19日 下午4:45:51
 */
public interface UserTradeDao extends BaseMapper<UserTradeEntity> {

    //交易列表(卖入)
    List<UserTradeBean> tradeList(Map<String, Object> param);

    Integer tradeListCount(Map<String, Object> param);

    List<UserTradeEntity> selectTrade(Map<String, Object> param);

    Integer selectTradeCount(Map<String, Object> param);

    //订单列表(卖入)
    List<UserTradeBean> selectOrderTrade(Map<String, Object> param);

    Integer selectOrderTradeCount(Map<String, Object> param);

    void updateTrade(Map<String, Object> param);

    List<Map<String, Object>> selectTradeUrl(Map<String, Object> param);

    /**
     * 查看订单明细
     *
     * @param param
     * @return
     */
    UserTradeBean queryDetail(Map<String, Object> param);

    UserTradeBean selectDetail(Map<String, Object> param);

    Integer insertUserTrade(UserTradeEntity userTradeEntity);

    List<UserTradeEntity> searchTrade(Map<String, Object> param);

    /**
     * 查看交易历史
     *
     * @param param
     * @return
     */
    List<UserTradeBean> selectTradeHistory(Map<String, Object> param);

    List<Map<String, Object>> getHistoryBystate5();

    UserTradeEntity selectById(String id);

    UserTradeBean buyMessage(@Param("id") String id);

    List<UserTradeEntity> getBystate(@Param("state") String id);

    //查询已完成订单
    List<UserTradeJoinBuyEntity> selectFinishOrder(Map<String, Object> map);

    //查询已完成订单
    Integer selectFinishOrderCount(Map<String, Object> map);


    UserTradeBean checkSuccess(Integer id);

}
