package cn.bitflash.controller;


import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInfoService;
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
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserInfoEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("realname", entity.getRealname());
        jsonObject.put("nickname", entity.getNickname());
        jsonObject.put("nicklock", entity.getNicklock());
        jsonObject.put("idNumber", entity.getIdNumber());
        jsonObject.put("mobile", entity.getMobile());
        jsonObject.put("vipCreateTime", entity.getVipCreateTime());
        jsonObject.put("authenticationTime", entity.getAuthenticationTime());
        jsonObject.put("isVip", entity.getIsVip());
        jsonObject.put("isInvitation", entity.getInvitation());
        jsonObject.put("isAuthentication", entity.getIsAuthentication());
        jsonObject.put("invitationCode", entity.getInvitationCode());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(json.getString("uid"));
        entity.setRealname(json.getString("realname"));
        entity.setNickname(json.getString("nickname"));
        entity.setNicklock(json.getString("nicklock"));
        entity.setIdNumber(json.getString("idNumber"));
        entity.setMobile(json.getString("mobile"));
        entity.setVipCreateTime(json.getDate("vipCreateTime"));
        entity.setAuthenticationTime(json.getDate("authenticationTime"));
        entity.setIsVip(json.getString("isVip"));
        entity.setIsInvitation(json.getBoolean("isInvitation"));
        entity.setIsAuthentication(json.getString("isAuthentication"));
        entity.setInvitationCode(json.getString("invitationCode"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInfo/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(json.getString("uid"));
        entity.setRealname(json.getString("realname"));
        entity.setNickname(json.getString("nickname"));
        entity.setNicklock(json.getString("nicklock"));
        entity.setIdNumber(json.getString("idNumber"));
        entity.setMobile(json.getString("mobile"));
        entity.setVipCreateTime(json.getDate("vipCreateTime"));
        entity.setAuthenticationTime(json.getDate("authenticationTime"));
        entity.setIsVip(json.getString("isVip"));
        entity.setIsInvitation(json.getBoolean("isInvitation"));
        entity.setIsAuthentication(json.getString("isAuthentication"));
        entity.setInvitationCode(json.getString("invitationCode"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
