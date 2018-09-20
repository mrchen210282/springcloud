package cn.bitflash.controller;


import cn.bitflash.entities.UserPayPwdEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayPwdService;
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
public class UserPayPwdController {

    @Autowired
    private UserPayPwdService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserPayPwdEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("payPassword", entity.getPayPassword());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserPayPwdEntity entity = new UserPayPwdEntity();
        entity.setUid(json.getString("uid"));
        entity.setPayPassword(json.getString("payPassword"));
        entity.setCreateTime(json.getDate("createTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserPayPwdEntity entity = new UserPayPwdEntity();
        entity.setUid(json.getString("uid"));
        entity.setPayPassword(json.getString("payPassword"));
        entity.setCreateTime(json.getDate("createTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
