package cn.bitflash.controller;


import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBuyHistoryService;
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
public class UserBuyHistoryController {

    @Autowired
    private UserBuyHistoryService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserBuyHistoryEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("orderState", entity.getOrderState());
        jsonObject.put("purchaseUid", entity.getPurchaseUid());
        jsonObject.put("quantity", entity.getQuantity());
        jsonObject.put("price", entity.getPrice());
        jsonObject.put("sellUid", entity.getSellUid());
        jsonObject.put("finishTime", entity.getFinishTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserBuyHistoryEntity entity = new UserBuyHistoryEntity();
        entity.setId(json.getString("id"));
        entity.setOrderState(json.getString("orderState"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setPrice(json.getFloat("price"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setFinishTime(json.getDate("finishTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserBuyHistoryEntity entity = new UserBuyHistoryEntity();
        entity.setId(json.getString("id"));
        entity.setOrderState(json.getString("orderState"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setQuantity(json.getFloat("quantity"));
        entity.setPrice(json.getFloat("price"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setFinishTime(json.getDate("finishTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
