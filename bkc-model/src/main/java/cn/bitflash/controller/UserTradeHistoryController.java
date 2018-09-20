package cn.bitflash.controller;


import cn.bitflash.entity.UserTradeConfigEntity;
import cn.bitflash.entity.UserTradeHistoryEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserTradeHistoryService;
import com.alibaba.fastjson.JSON;
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
public class UserTradeHistoryController {

    @Autowired
    private UserTradeHistoryService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userTradeHistory/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserTradeHistoryEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userTradeId", entity.getUserTradeId());
        jsonObject.put("purchaseUid", entity.getPurchaseUid());
        jsonObject.put("purchaseQuantity", entity.getPurchaseQuantity());
        jsonObject.put("sellUid", entity.getSellUid());
        jsonObject.put("sellQuantity", entity.getSellQuantity());
        jsonObject.put("orderState", entity.getOrderState());
        jsonObject.put("price", entity.getPrice());
        jsonObject.put("finishTime", entity.getFinishTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userTradeHistory/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserTradeHistoryEntity entity = new UserTradeHistoryEntity();
        entity.setUserTradeId(json.getString("userTradeId"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setPurchaseQuantity(json.getBigDecimal("purchaseQuantity"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setSellQuantity(json.getBigDecimal("sellQuantity"));
        entity.setOrderState(json.getString("orderState"));
        entity.setPrice(json.getBigDecimal("price"));
        entity.setFinishTime(json.getDate("finishTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userTradeHistory/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserTradeHistoryEntity entity = new UserTradeHistoryEntity();
        entity.setUserTradeId(json.getString("userTradeId"));
        entity.setPurchaseUid(json.getString("purchaseUid"));
        entity.setPurchaseQuantity(json.getBigDecimal("purchaseQuantity"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setSellQuantity(json.getBigDecimal("sellQuantity"));
        entity.setOrderState(json.getString("orderState"));
        entity.setPrice(json.getBigDecimal("price"));
        entity.setFinishTime(json.getDate("finishTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userTradeHistory/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
