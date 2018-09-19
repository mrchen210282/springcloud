package cn.bitflash.controller;


import cn.bitflash.entity.UserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.LoginService;
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
public class LoginController {

    @Autowired
    private LoginService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/login/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("uuid", entity.getUuid());
        jsonObject.put("password", entity.getPassword());
        jsonObject.put("mobile", entity.getMobile());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/login/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserEntity entity = new UserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setPassword(json.getString("password"));
        entity.setUuid(json.getString("uuid"));
        entity.setUid(json.getString("uid"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/login/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserEntity entity = new UserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setPassword(json.getString("password"));
        entity.setUuid(json.getString("uuid"));
        entity.setUid(json.getString("uid"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/login/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }
}
