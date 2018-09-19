package cn.bitflash.controller;


import cn.bitflash.entity.PlatformConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.PlatFormConfigService;
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
public class PlatFormConfigController {

    @Autowired
    private PlatFormConfigService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        PlatformConfigEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", entity.getId());
        jsonObject.put("unit", entity.getUnit());
        jsonObject.put("configName", entity.getConfigName());
        jsonObject.put("configValue", entity.getConfigValue());
        jsonObject.put("updateTime", entity.getUpdateTime());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        PlatformConfigEntity entity = new PlatformConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setUnit(json.getString("unit"));
        entity.setConfigName(json.getString("configName"));
        entity.setConfigValue(json.getString("configValue"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        PlatformConfigEntity entity = new PlatformConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setUnit(json.getString("unit"));
        entity.setConfigName(json.getString("configName"));
        entity.setConfigValue(json.getString("configValue"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }

}
