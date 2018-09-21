//package cn.bitflash.controller;
//
//
//import cn.bitflash.entities.UserTradeConfigEntity;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.service.UserTradeConfigService;
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
//public class UserTradeConfigController {
//
//    @Autowired
//    private UserTradeConfigService userTradeConfigService;
//
//    /**
//     * selectById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeConfig/selectById")
//    public UserTradeConfigEntity selectById(@RequestParam("id") Integer id) {
//        UserTradeConfigEntity entity = userTradeConfigService.selectById(id);
//        return entity;
//    }
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeConfig/updateById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void updateById(@RequestBody JSONObject json) {
//        UserTradeConfigEntity entity = new UserTradeConfigEntity();
//        entity.setId(json.getInteger("id"));
//        entity.setPoundage(json.getFloat("poundage"));
//        entity.setRemark(json.getString("remark"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeConfigService.updateById(entity);
//    }
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeConfig/insert")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void insert(@RequestBody JSONObject json) {
//        UserTradeConfigEntity entity = new UserTradeConfigEntity();
//        entity.setId(json.getInteger("id"));
//        entity.setPoundage(json.getFloat("poundage"));
//        entity.setRemark(json.getString("remark"));
//        entity.setCreateTime(json.getDate("createTime"));
//        userTradeConfigService.insert(entity);
//    }
//
//    /**
//     * deleteById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userTradeConfig/deleteById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void deleteById(@RequestParam("id") Integer id) {
//        userTradeConfigService.deleteById(id);
//    }
//
//}
