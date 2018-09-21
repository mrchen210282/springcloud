//
//package cn.bitflash.service.impl;
//
//import cn.bitflash.dao.UserTradeDao;
//import cn.bitflash.entities.UserTradeEntity;
//import cn.bitflash.entity.UserTradeBean;
//import cn.bitflash.entity.UserTradeJoinBuyEntity;
//import cn.bitflash.service.UserTradeService;
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author wangjun
// * @date 2018年6月19日 下午4:48:48
// */
//
//
//@Service("userTradeService")
//public class UserTradeServiceImpl extends ServiceImpl<UserTradeDao, UserTradeEntity> implements UserTradeService {
//
//
//    @Override
//    public Integer selectTradeCount(Map<String, Object> param) {
//        Integer count = baseMapper.selectTradeCount(param);
//        return count;
//    }
//
//    @Override
//    public List<UserTradeEntity> selectTrade(Map<String, Object> param) {
//        List<UserTradeEntity> list = baseMapper.selectTrade(param);
//        return list;
//    }
//
//
//    @Override
//    public void updateTrade(Map<String, Object> param) {
//        baseMapper.updateTrade(param);
//    }
//
//    @Override
//    public List<Map<String, Object>> selectTradeUrl(Map<String, Object> param) {
//        List<Map<String, Object>> list = baseMapper.selectTradeUrl(param);
//        return list;
//    }
//
//
///**
//     * 查看订单明细
//     *
//     * @param param
//     * @return
//     */
//
//    @Override
//    public UserTradeBean queryDetail(Map<String, Object> param) {
//        UserTradeBean userTradeBean = baseMapper.queryDetail(param);
//        return userTradeBean;
//    }
//
//
///**
//     * 查看订单明细
//     *
//     * @param param
//     * @return
//     */
//
//    @Override
//    public UserTradeBean selectDetail(Map<String, Object> param) {
//        UserTradeBean userTradeBean = baseMapper.selectDetail(param);
//        return userTradeBean;
//    }
//
//
///**
//     * 添加交易记录
//     */
//
//    @Override
//    public Integer insertUserTrade(UserTradeEntity userTradeEntity) {
//        Integer i = baseMapper.insertUserTrade(userTradeEntity);
//        return i;
//    }
//
//    @Override
//    public List<UserTradeBean> selectTradeHistory(Map<String, Object> param) {
//        List<UserTradeBean> list = baseMapper.selectTradeHistory(param);
//        return list;
//    }
//
//    @Override
//    public UserTradeEntity selectById(String id) {
//        return baseMapper.selectById(id);
//    }
//
//
//    public UserTradeBean buyMessage(String id) {
//
//        UserTradeBean userTradeBean = baseMapper.buyMessage(id);
//        return userTradeBean;
//    }
//
//    @Override
//    public List<UserTradeEntity> getByState(String state) {
//        return baseMapper.getBystate(state);
//    }
//
//    //查询已完成订单
//    @Override
//    public List<UserTradeJoinBuyEntity> selectFinishOrder(Map<String, Object> map) {
//        return baseMapper.selectFinishOrder(map);
//    }
//
//    //查询已完成订单
//    @Override
//    public Integer selectFinishOrderCount(Map<String, Object> map) {
//        return baseMapper.selectFinishOrderCount(map);
//    }
//
//
//    @Override
//    public UserTradeBean checkSuccess(Integer id) {
//        return baseMapper.checkSuccess(id);
//    }
//
//}
//
//
