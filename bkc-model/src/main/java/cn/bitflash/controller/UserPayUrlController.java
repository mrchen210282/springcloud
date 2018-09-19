package cn.bitflash.controller;


import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayUrlService;
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
public class UserPayUrlController {

    @Autowired
    private UserPayUrlService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserPayUrlEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("imgType", entity.getImgType());
        jsonObject.put("imgUrl", entity.getImgUrl());
        jsonObject.put("mobile", entity.getMobile());
        jsonObject.put("name", entity.getName());
        jsonObject.put("account", entity.getAccount());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserPayUrlEntity entity = new UserPayUrlEntity();
        entity.setId(json.getInteger("id"));
        entity.setUid(json.getString("uid"));
        entity.setImgType(json.getString("imgType"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setMobile(json.getString("mobile"));
        entity.setName(json.getString("name"));
        entity.setAccount(json.getString("account"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserPayUrlEntity entity = new UserPayUrlEntity();
        entity.setId(json.getInteger("id"));
        entity.setUid(json.getString("uid"));
        entity.setImgType(json.getString("imgType"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setMobile(json.getString("mobile"));
        entity.setName(json.getString("name"));
        entity.setAccount(json.getString("account"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
