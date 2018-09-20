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
    private UserSendService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userSend/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        UserSendEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("sendUid", entity.getSendeeUid());
        jsonObject.put("quantity", entity.getQuantity());
        jsonObject.put("sendeeUid", entity.getSendeeUid());
        jsonObject.put("uuid", entity.getUuid());
        jsonObject.put("sendTime", entity.getSendTime());
        return jsonObject;
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
        entity.setUuid(json.getString("uuid"));
        entity.setSendTime(json.getDate("sendTime"));
        service.updateById(entity);
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
        entity.setUuid(json.getString("uuid"));
        entity.setSendTime(json.getDate("sendTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userSend/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }

    /**
     *selectaccountcount
     */
    @PostMapping("/inner/userSend/selectaccountcount")
    Integer selectaccountcount(@RequestParam("uid")String uid){
        return service.selectaccountcount(uid);
    }

    /**
     * selectacceptcount
     */
    @PostMapping("/inner/userSend/selectaccountcount")
    Integer selectacceptcount(String send_uid){
        return service.selectacceptcount(send_uid);
    }

    /**
     *selectAccount
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userSend/selectAccount")
    List<UserSendEntity> selectAccount(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages){
        List<UserSendEntity> userSendEntities = service.selectaccount(uid,pages);
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
        List<UserSendEntity> userSendEntities = service.selectaccept(uid,pages);
        return userSendEntities;
    }
}
