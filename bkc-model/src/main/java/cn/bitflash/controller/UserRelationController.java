package cn.bitflash.controller;


import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserRelationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserRelationController {

    @Autowired
    private UserRelationService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserRelationEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("invitation_code", entity.getInvitation_code());
        jsonObject.put("lft", entity.getLft());
        jsonObject.put("rgt", entity.getRgt());
        jsonObject.put("layer", entity.getLayer());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserRelationEntity entity = new UserRelationEntity();
        entity.setUid(json.getString("uid"));
        entity.setInvitation_code(json.getString("invitation_code"));
        entity.setLft(json.getInteger("lft"));
        entity.setRgt(json.getInteger("rgt"));
        entity.setLayer(json.getInteger("layer"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userRelation/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserRelationEntity entity = new UserRelationEntity();
        entity.setUid(json.getString("uid"));
        entity.setInvitation_code(json.getString("invitation_code"));
        entity.setLft(json.getInteger("lft"));
        entity.setRgt(json.getInteger("rgt"));
        entity.setLayer(json.getInteger("layer"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
