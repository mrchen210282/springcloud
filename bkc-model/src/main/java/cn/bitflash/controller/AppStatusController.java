package cn.bitflash.controller;


import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.AppStatusService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @author GAOYGUUO
 */
@RestController
@RequestMapping("test")
public class AppStatusController {

    @Autowired
    private AppStatusService appStatusService;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    @PostMapping("testCode")
    public JSONObject selectOne(@RequestParam String appid) {
        AppStatusEntity app = appStatusService.selectById(appid);
        JSONObject json =new JSONObject();
        json.put("version",app.getVersion());
        json.put("url",app.getUrl());
        json.put("note",app.getNote());
        json.put("title",app.getTitle());
        return json;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(AppStatusEntity appStatusEntity) {
        appStatusService.updateById(appStatusEntity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(AppStatusEntity appStatusEntity) {
        appStatusService.insert(appStatusEntity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        appStatusService.deleteById(id);
    }

}
