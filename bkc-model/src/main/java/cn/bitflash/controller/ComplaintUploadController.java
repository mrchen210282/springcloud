package cn.bitflash.controller;


import cn.bitflash.entity.ComplaintUploadEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.ComplaintUploadService;
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
public class ComplaintUploadController {

    @Autowired
    private ComplaintUploadService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/complaintUpload/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id) {
        ComplaintUploadEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("complaintId", entity.getComplaintId());
        jsonObject.put("imgUrl", entity.getImgUrl());
        jsonObject.put("remark", entity.getRemark());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/complaintUpload/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        ComplaintUploadEntity entity = new ComplaintUploadEntity();
        entity.setComplaintId(json.getInteger("complaintId"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setRemark(json.getString("remark"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/complaintUpload/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        ComplaintUploadEntity entity = new ComplaintUploadEntity();
        entity.setComplaintId(json.getInteger("complaintId"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setRemark(json.getString("remark"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/complaintUpload/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        service.deleteById(id);
    }

}
