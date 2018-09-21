package cn.bitflash.controller;

import cn.bitflash.entities.AuthorityUserEntity;
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
    private AuthorityUserService authorityUserService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/selectById")
    public AuthorityUserEntity selectById(@RequestParam("id") String id) {
        AuthorityUserEntity entity = authorityUserService.selectById(id);
        return entity;
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
        authorityUserService.updateById(entity);
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
        authorityUserService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/authorityUser/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        authorityUserService.deleteById(id);
    }

}
