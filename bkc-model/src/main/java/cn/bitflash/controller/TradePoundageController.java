//package cn.bitflash.controller;
//
//
//import cn.bitflash.entities.TradePoundageEntity;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.service.TradePoundageService;
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
// * @author GAOYGUUO
// */
//@RestController
//public class TradePoundageController {
//
//    @Autowired
//    private TradePoundageService tradePoundageService;
//
//    /**
//     * selectById
//     *
//     * @return
//     */
//    @PostMapping("/inner/tradePoundage/selectById")
//    public TradePoundageEntity selectById(@RequestParam("id") String id) {
//        TradePoundageEntity entity = tradePoundageService.selectById(id);
//        return entity;
//    }
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/tradePoundage/updateById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void updateById(@RequestBody JSONObject json) {
//        TradePoundageEntity entity = new TradePoundageEntity();
//        entity.setUid(json.getString("uid"));
//        entity.setUserTradeId(json.getString("userTradeId"));
//        entity.setPoundage(json.getBigDecimal("poundage"));
//        entity.setCreateTime(json.getDate("createTime"));
//        tradePoundageService.updateById(entity);
//    }
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/tradePoundage/insert")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void insert(@RequestBody JSONObject json) {
//        TradePoundageEntity entity = new TradePoundageEntity();
//        entity.setUid(json.getString("uid"));
//        entity.setUserTradeId(json.getString("userTradeId"));
//        entity.setPoundage(json.getBigDecimal("poundage"));
//        entity.setCreateTime(json.getDate("createTime"));
//        tradePoundageService.insert(entity);
//    }
//
//    /**
//     * deleteById
//     *
//     * @return
//     */
//    @PostMapping("/inner/tradePoundage/deleteById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void deleteById(@RequestParam("id") String id) {
//        tradePoundageService.deleteById(id);
//    }
//
//}
