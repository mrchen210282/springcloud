package cn.bitflash.service;

import cn.bitflash.entities.UserTradeEntity;
import cn.bitflash.entity.UserTradeBean;
import cn.bitflash.entity.UserTradeJoinBuyEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author wangjun
 * @date 2018年6月19日 下午4:47:36
 */
public interface UserTradeService extends IService<UserTradeEntity> {

    //交易列表(卖入)
    List<UserTradeBean> tradeList(Map<String, Object> param);

    Integer tradeListCount(Map<String, Object> param);

    Map<String, Object> responseTrade(Map<String, Object> param);

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

    /**
     * 添加交易记录
     */
    Integer insertUserTrade(UserTradeEntity userTradeEntity);

    /**
     * 查看交易历史
     *
     * @param param
     * @return
     */
    List<UserTradeBean> selectTradeHistory(Map<String, Object> param);

    UserTradeEntity selectById(String id);

    List<UserTradeEntity> getByState(String state);

    //查询已完成订单
    List<UserTradeJoinBuyEntity> selectFinishOrder(Map<String, Object> map);

    //查询已完成订单
    Integer selectFinishOrderCount(Map<String, Object> map);


    UserTradeBean checkSuccess(Integer id);


}
