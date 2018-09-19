package cn.bitflash.controller;


import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBuyService;
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
public class UserBuyController {

    @Autowired
    private UserBuyService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userBuy/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserBuyEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("purchaseUid", entity.getPurchaseUid());
        jsonObject.put("quantity", entity.getQuantity());
        jsonObject.put("price", entity.getPrice());
        jsonObject.put("sellUid", entity.getSellUid());
        jsonObject.put("state", entity.getState());
        jsonObject.put("createTime", entity.getCreateTime());
        jsonObject.put("payTime", entity.getPayTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBuy/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserBuyEntity entity = new UserBuyEntity();
        entity.setId(json.getString("id"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setPrice(json.getFloat("price"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setState(json.getString("state"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setPayTime(json.getDate("payTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBuy/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserBuyEntity entity = new UserBuyEntity();
        entity.setId(json.getString("id"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setPrice(json.getFloat("price"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setState(json.getString("state"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setPayTime(json.getDate("payTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBuy/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }
}
