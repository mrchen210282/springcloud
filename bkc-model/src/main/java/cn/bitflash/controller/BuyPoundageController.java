package cn.bitflash.controller;


import cn.bitflash.entities.BuyPoundageEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.BuyPoundageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class BuyPoundageController {

    @Autowired
    private BuyPoundageService service;


    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/buyPoundage/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        BuyPoundageEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userBuyId", entity.getUserBuyId());
        jsonObject.put("sellUid", entity.getSellUid());
        jsonObject.put("poundage", entity.getPoundage());
        jsonObject.put("quantity", entity.getQuantity());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        BuyPoundageEntity entity = new BuyPoundageEntity();
        entity.setUserBuyId(json.getString("userBuyId"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setPoundage(json.getBigDecimal("poundage"));
        entity.setQuantity(json.getBigDecimal("quantity"));
        entity.setCreateTime(json.getDate("createTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        BuyPoundageEntity entity = new BuyPoundageEntity();
        entity.setUserBuyId(json.getString("userBuyId"));
        entity.setSellUid(json.getString("sellUid"));
        entity.setPoundage(json.getBigDecimal("poundage"));
        entity.setQuantity(json.getBigDecimal("quantity"));
        entity.setCreateTime(json.getDate("createTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
