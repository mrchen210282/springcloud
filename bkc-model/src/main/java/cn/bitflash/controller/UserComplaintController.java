package cn.bitflash.controller;


import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserComplaintService;
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
public class UserComplaintController {

    @Autowired
    private UserComplaintService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserComplaintEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", entity.getOrderId());
        jsonObject.put("complaintUid", entity.getComplaintUid());
        jsonObject.put("contactsUid", entity.getContactsUid());
        jsonObject.put("complaintState", entity.getComplaintState());
        jsonObject.put("orderState", entity.getOrderId());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserComplaintEntity entity = new UserComplaintEntity();
        entity.setOrderId(json.getString("orderId"));
        entity.setComplaintUid(json.getString("complaintUid"));
        entity.setContactsUid(json.getString("contactsUid"));
        entity.setComplaintState(json.getString("complaintState"));
        entity.setOrderId(json.getString("orderState"));
        entity.setCreateTime(json.getDate("createTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserComplaintEntity entity = new UserComplaintEntity();
        entity.setOrderId(json.getString("orderId"));
        entity.setComplaintUid(json.getString("complaintUid"));
        entity.setContactsUid(json.getString("contactsUid"));
        entity.setComplaintState(json.getString("complaintState"));
        entity.setOrderId(json.getString("orderState"));
        entity.setCreateTime(json.getDate("createTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }

}
