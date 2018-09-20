package cn.bitflash.controller;


import cn.bitflash.entities.UserInvitationCodeEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInvitationCodeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
public class UserInvitationCodeController {

    @Autowired
    private UserInvitationCodeService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserInvitationCodeEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("lftCode", entity.getLftCode());
        jsonObject.put("rgtCode", entity.getRgtCode());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserInvitationCodeEntity entity = new UserInvitationCodeEntity();
        entity.setUid(json.getString("uid"));
        entity.setLftCode(json.getString("lftCode"));
        entity.setRgtCode(json.getString("rgtCode"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserInvitationCodeEntity entity = new UserInvitationCodeEntity();
        entity.setUid(json.getString("uid"));
        entity.setLftCode(json.getString("lftCode"));
        entity.setRgtCode(json.getString("rgtCode"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

    /**
     * selectCodeByCode
     */
    @PostMapping("/inner/userInvitationCode/selectCodeByCode")
    UserInvitationCodeEntity selectCodeByCode(@RequestParam("code")String code){
        UserInvitationCodeEntity entity = service.selectOne(new EntityWrapper<UserInvitationCodeEntity>().eq("lft_code",code));
        if(entity == null){
            entity = service.selectOne(new EntityWrapper<UserInvitationCodeEntity>().eq("rgt_code",code));
        }
        return entity;
    }

}
