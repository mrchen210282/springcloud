package cn.bitflash.controller;


import cn.bitflash.entity.TokenEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.TokenService;
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
public class TokenController {

    @Autowired
    private TokenService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/token/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        TokenEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("token", entity.getToken());
        jsonObject.put("mobile", entity.getMobile());
        jsonObject.put("updateTime", entity.getUpdateTime());
        jsonObject.put("expireTime", entity.getExpireTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/token/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        TokenEntity entity = new TokenEntity();
        entity.setUid(json.getString("uid"));
        entity.setToken(json.getString("token"));
        entity.setMobile(json.getString("mobile"));
        entity.setExpireTime(json.getDate("expireTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/token/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        TokenEntity entity = new TokenEntity();
        entity.setUid(json.getString("uid"));
        entity.setToken(json.getString("token"));
        entity.setMobile(json.getString("mobile"));
        entity.setExpireTime(json.getDate("expireTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/token/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
