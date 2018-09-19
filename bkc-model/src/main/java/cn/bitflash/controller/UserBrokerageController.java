package cn.bitflash.controller;


import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBrokerageService;
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
public class UserBrokerageController {

    @Autowired
    private UserBrokerageService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userBrokerage/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        UserBrokerageEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("purchaseBrokerage", entity.getPurchaseBrokerage());
        jsonObject.put("sellBrokerage", entity.getSellBrokerage());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBrokerage/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserBrokerageEntity entity = new UserBrokerageEntity();
        entity.setId(json.getInteger("id"));
        entity.setPurchaseBrokerage(json.getBigDecimal("purchaseBrokerage"));
        entity.setSellBrokerage(json.getBigDecimal("sellBrokerage"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBrokerage/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserBrokerageEntity entity = new UserBrokerageEntity();
        entity.setId(json.getInteger("id"));
        entity.setPurchaseBrokerage(json.getBigDecimal("purchaseBrokerage"));
        entity.setSellBrokerage(json.getBigDecimal("sellBrokerage"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBrokerage/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }

}
