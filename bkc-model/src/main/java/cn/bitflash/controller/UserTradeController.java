//package cn.bitflash.controller;
//
//
//import cn.bitflash.entities.UserTradeEntity;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.service.UserTradeService;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author GAOYUGUO
// */
//@RestController
//public class UserTradeController {
//
//    @Autowired
//    private UserTradeService userTradeService;
//
//    /**
//     * selectById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTrade/selectById")
//    public UserTradeEntity selectById(@RequestParam("id") String id) {
//        UserTradeEntity entity = userTradeService.selectById(id);
//        return entity;
//    }
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTrade/updateById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void updateById(@RequestBody JSONObject json) {
//        UserTradeEntity entity = new UserTradeEntity();
//        entity.setId(json.getString("id"));
//        entity.setUid(json.getString("uid"));
//        entity.setState(json.getString("state"));
//        entity.setQuantity(json.getBigDecimal("quantity"));
//        entity.setPrice(json.getBigDecimal("price"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeService.updateById(entity);
//    }
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTrade/insert")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void insert(@RequestBody JSONObject json) {
//        UserTradeEntity entity = new UserTradeEntity();
//        entity.setId(json.getString("id"));
//        entity.setUid(json.getString("uid"));
//        entity.setState(json.getString("state"));
//        entity.setQuantity(json.getBigDecimal("quantity"));
//        entity.setPrice(json.getBigDecimal("price"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeService.insert(entity);
//    }
//
//    /**
//     * deleteById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTrade/deleteById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void deleteById(@RequestParam("id") String id) {
//        userTradeService.deleteById(id);
//    }
//
//    /**
//     *insertOrUpdateTrade
//     */
//    @PostMapping("/inner/userTrade/insertOrUpdateTrade")
//    boolean insertOrUpdateTrade(@RequestBody UserTradeEntity trade){
//        return userTradeService.insertOrUpdate(trade);
//    }
//
//    /**
//     * selectTradeByIdAndState
//     */
//    @PostMapping("/inner/userTrade/selectTradeByIdAndState")
//    UserTradeEntity selectTradeByIdAndState(@RequestParam("id") String id,@RequestParam("state") String state){
//        UserTradeEntity entity = userTradeService.selectOne(new EntityWrapper<UserTradeEntity>().eq("id",id).eq("state",state));
//        return entity;
//    }
//}
