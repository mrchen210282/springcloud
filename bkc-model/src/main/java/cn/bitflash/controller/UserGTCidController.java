package cn.bitflash.controller;

import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserGTCidService;
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
public class UserGTCidController {

    @Autowired
    private UserGTCidService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userGTCid/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        UserGTCidEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("cid", entity.getCid());
        jsonObject.put("updateTime", entity.getUpdateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userGTCid/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserGTCidEntity entity = new UserGTCidEntity();
        entity.setId(json.getInteger("id"));
        entity.setUid(json.getString("uid"));
        entity.setCid(json.getString("cid"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userGTCid/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserGTCidEntity entity = new UserGTCidEntity();
        entity.setId(json.getInteger("id"));
        entity.setUid(json.getString("uid"));
        entity.setCid(json.getString("cid"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userGTCid/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }


}
