package cn.bitflash.controller;

import cn.bitflash.entities.UserSendEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserSendService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserSendController {

    @Autowired
    private UserSendService userSendService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userSend/selectById")
    public UserSendEntity selectById(@RequestParam("id") Integer id) {
        UserSendEntity entity = userSendService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userSend/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserSendEntity entity = new UserSendEntity();
        entity.setId(json.getInteger("id"));
        entity.setSendUid(json.getString("sendUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setSendeeUid(json.getString("sendeeUid"));
        entity.setSendTime(json.getDate("sendTime"));
        userSendService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userSend/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserSendEntity entity = new UserSendEntity();
        entity.setId(json.getInteger("id"));
        entity.setSendUid(json.getString("sendUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setSendeeUid(json.getString("sendeeUid"));
        entity.setSendTime(json.getDate("sendTime"));
        userSendService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userSend/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        userSendService.deleteById(id);
    }

    /**
     *selectaccountcount
     */
    @PostMapping("/inner/userSend/selectaccountcount")
    Integer selectaccountcount(@RequestParam("uid")String uid){
        return userSendService.selectaccountcount(uid);
    }

    /**
     * selectacceptcount
     */
    @PostMapping("/inner/userSend/selectaccountcount")
    Integer selectacceptcount(String send_uid){
        return userSendService.selectacceptcount(send_uid);
    }

    /**
     *selectAccount
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userSend/selectAccount")
    List<UserSendEntity> selectAccount(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages){
        List<UserSendEntity> userSendEntities = userSendService.selectaccount(uid,pages);
        return userSendEntities;
    }

    /**
     *selectaccept
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userSend/selectaccept")
    List<UserSendEntity> selectaccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages){
        List<UserSendEntity> userSendEntities = userSendService.selectaccept(uid,pages);
        return userSendEntities;
    }
}
