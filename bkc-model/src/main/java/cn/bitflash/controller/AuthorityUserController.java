package cn.bitflash.controller;

import cn.bitflash.entity.AuthorityUserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.AuthorityUserService;
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
public class AuthorityUserController {
    @Autowired
    private AuthorityUserService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        AuthorityUserEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", entity.getMobile());
        jsonObject.put("ticket", entity.getTicket());
        jsonObject.put("uid", entity.getUid());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        AuthorityUserEntity entity = new AuthorityUserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setTicket(json.getString("ticket"));
        entity.setUid(json.getString("uid"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        AuthorityUserEntity entity = new AuthorityUserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setTicket(json.getString("ticket"));
        entity.setUid(json.getString("uid"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
