//package cn.bitflash.controller;
//
//
//import cn.bitflash.entities.UserTradeHistoryEntity;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.service.UserTradeHistoryService;
//import com.alibaba.fastjson.JSONObject;
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
//public class UserTradeHistoryController {
//
//    @Autowired
//    private UserTradeHistoryService userTradeHistoryService;
//
//    /**
//     * selectById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeHistory/selectById")
//    public UserTradeHistoryEntity selectById(@RequestParam("id") String id) {
//        UserTradeHistoryEntity entity = userTradeHistoryService.selectById(id);
//        return entity;
//    }
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeHistory/updateById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void updateById(@RequestBody JSONObject json) {
//        UserTradeHistoryEntity entity = new UserTradeHistoryEntity();
//        entity.setUserTradeId(json.getString("userTradeId"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setPurchaseQuantity(json.getBigDecimal("purchaseQuantity"));
//        entity.setSellUid(json.getString("sellUid"));
//        entity.setSellQuantity(json.getBigDecimal("sellQuantity"));
//        entity.setOrderState(json.getString("orderState"));
//        entity.setPrice(json.getBigDecimal("price"));
//        entity.setFinishTime(json.getDate("finishTime"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeHistoryService.updateById(entity);
//    }
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeHistory/insert")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void insert(@RequestBody JSONObject json) {
//        UserTradeHistoryEntity entity = new UserTradeHistoryEntity();
//        entity.setUserTradeId(json.getString("userTradeId"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setPurchaseQuantity(json.getBigDecimal("purchaseQuantity"));
//        entity.setSellUid(json.getString("sellUid"));
//        entity.setSellQuantity(json.getBigDecimal("sellQuantity"));
//        entity.setOrderState(json.getString("orderState"));
//        entity.setPrice(json.getBigDecimal("price"));
//        entity.setFinishTime(json.getDate("finishTime"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeHistoryService.insert(entity);
//    }
//
//    /**
//     * deleteById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeHistory/deleteById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void deleteById(@RequestParam("id") String id) {
//        userTradeHistoryService.deleteById(id);
//    }
//
//}
