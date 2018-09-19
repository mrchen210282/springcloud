package cn.bitflash.controller;


import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.TradePoundageService;
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
public class TradePoundageController {

    @Autowired
    private TradePoundageService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        TradePoundageEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("userTradeId", entity.getUserTradeId());
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
    @PostMapping("/inner/tradePoundage/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        TradePoundageEntity entity = new TradePoundageEntity();
        entity.setUserTradeId(json.getString("userTradeId"));
        entity.setUid(json.getString("uid"));
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
    @PostMapping("/inner/tradePoundage/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        TradePoundageEntity entity = new TradePoundageEntity();
        entity.setUserTradeId(json.getString("userTradeId"));
        entity.setUid(json.getString("uid"));
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
    @PostMapping("/inner/tradePoundage/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
