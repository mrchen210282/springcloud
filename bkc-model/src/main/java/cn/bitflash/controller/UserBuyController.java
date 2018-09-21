//package cn.bitflash.controller;
//
//
//import cn.bitflash.entity.UserBuyBean;
//import cn.bitflash.entities.UserBuyEntity;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.service.UserBuyService;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @author GAOYUGUO
// */
//
//@RestController
//public class UserBuyController {
//
//    @Autowired
//    private UserBuyService userBuyService;
//
//    /**
//     * selectById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userBuy/selectById")
//    public UserBuyEntity selectById(@RequestParam("id") String id) {
//        UserBuyEntity entity = userBuyService.selectById(id);
//        return entity;
//    }
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userBuy/updateById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void updateById(@RequestBody JSONObject json) {
//        UserBuyEntity entity = new UserBuyEntity();
//        entity.setId(json.getString("id"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setQuantity(json.getFloat("quantity"));
//        entity.setPrice(json.getFloat("price"));
//        entity.setSellUid(json.getString("sellUid"));
//        entity.setState(json.getString("state"));
//        entity.setCreateTime(json.getDate("createTime"));
//        entity.setPayTime(json.getDate("payTime"));
//        userBuyService.updateById(entity);
//    }
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/userBuy/insert")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void insert(@RequestBody JSONObject json) {
//        UserBuyEntity entity = new UserBuyEntity();
//        entity.setId(json.getString("id"));
//        entity.setPurchaseUid(json.getString("purchaseUid"));
//        entity.setQuantity(json.getFloat("quantity"));
//        entity.setPrice(json.getFloat("price"));
//        entity.setSellUid(json.getString("sellUid"));
//        entity.setState(json.getString("state"));
//        entity.setCreateTime(json.getDate("createTime"));
//        entity.setPayTime(json.getDate("payTime"));
//        userBuyService.insert(entity);
//    }
//
//    /**
//     * deleteById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userBuy/deleteById")
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
//    public void deleteById(@RequestParam("id") String id) {
//        userBuyService.deleteById(id);
//    }
//
//    /**
//     * showBuying
//     *
//     * @param uid
//     * @param pages
//     * @return
//     */
//    @PostMapping("/inner/userBuy/showBuying")
//    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages) {
//        List<UserBuyBean> userBuyBeans = userBuyService.showBuying(uid, pages);
//        return userBuyBeans;
//    }
//
//    /**
//     * showOrder
//     *
//     * @param uid
//     * @param pages
//     * @return
//     */
//    @PostMapping("/inner/userBuy/showOrder")
//    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages){
//        List<UserBuyBean> userBuyBeans = userBuyService.showOrder(uid, pages);
//        return userBuyBeans;
//    }
//
//    /**
//     * showBuyingCount
//     *
//     * @return
//     */
//    @PostMapping("/inner/userBuy/showBuyingCount")
//    Integer showBuyingCount(@RequestParam("uid") String uid){
//        int count = userBuyService.showBuyingCount(uid);
//        return count;
//    }
//
//    /**
//     * showOrderCount
//     *
//     * @param uid
//     * @return
//     */
//    @PostMapping("/inner/userBuy/showOrderCount")
//    Integer showOrderCount(@RequestParam("uid") String uid){
//        int count = userBuyService.showOrderCount(uid);
//        return count;
//    }
//
//    /**
//     * checkOrder
//     *
//     */
//    @PostMapping("/inner/userBuy/checkOrder")
//    UserBuyBean checkOrder(@RequestParam("id")String id){
//        UserBuyBean userBuyBean = userBuyService.checkOrder(id);
//        return userBuyBean;
//    }
//}
