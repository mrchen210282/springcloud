package cn.bitflash.controller;


import cn.bitflash.entity.UserEmpowerEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserEmpowerService;
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
public class UserEmpowerController {

    @Autowired
    private UserEmpowerService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        UserEmpowerEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("appid", entity.getAppid());
        jsonObject.put("appkey", entity.getAppkey());
        jsonObject.put("ticket", entity.getTicket());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserEmpowerEntity entity = new UserEmpowerEntity();
        entity.setId(json.getInteger("id"));
        entity.setAppid(json.getString("appid"));
        entity.setAppkey(json.getString("appkey"));
        entity.setTicket(json.getString("ticket"));
        entity.setCreateTime(json.getDate("createTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserEmpowerEntity entity = new UserEmpowerEntity();
        entity.setId(json.getInteger("id"));
        entity.setAppid(json.getString("appid"));
        entity.setAppkey(json.getString("appkey"));
        entity.setTicket(json.getString("ticket"));
        entity.setCreateTime(json.getDate("createTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }

}
