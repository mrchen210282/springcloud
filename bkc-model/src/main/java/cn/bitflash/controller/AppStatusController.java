package cn.bitflash.controller;


import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.AppStatusService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @author GAOYGUUO
 * @author GAOYUGUO
 */
@RestController
public class AppStatusController {

    @Autowired
    private AppStatusService appStatusService;
    private AppStatusService service;

    /**
     * selectById
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/appStatus/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        AppStatusEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", entity.getUrl());
        jsonObject.put("title", entity.getTitle());
        jsonObject.put("note", entity.getNote());
        jsonObject.put("appId", entity.getAppid());
        jsonObject.put("version", entity.getVersion());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/updateById")
    public void updateById(@RequestBody JSONObject json) {
        AppStatusEntity entity = new AppStatusEntity();
        entity.setAppid(json.getString("appId"));
        entity.setUrl(json.getString("url"));
        entity.setTitle(json.getString("title"));
        entity.setNote(json.getString("note"));
        entity.setVersion(json.getString("version"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/appStatus/insert")
    public void insert(@RequestBody JSONObject json) {
        AppStatusEntity entity = new AppStatusEntity();
        entity.setAppid(json.getString("appId"));
        entity.setUrl(json.getString("url"));
        entity.setTitle(json.getString("title"));
        entity.setNote(json.getString("note"));
        entity.setVersion(json.getString("version"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/deleteById")
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
