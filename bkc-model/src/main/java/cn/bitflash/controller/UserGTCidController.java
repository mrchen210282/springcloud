package cn.bitflash.controller;

import cn.bitflash.entities.UserGTCidEntity;
import cn.bitflash.service.UserGTCidService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GAOYGUUO
 */
@RestController
@RequestMapping("getui")
public class UserGTCidController {

    @Autowired
    private UserGTCidService service;


    /**
     * selectCid
     *
     * @return
     */
    @PostMapping("/inner/userGTCidEntity/selectCid")
    String selectCid(@RequestParam("uid") String uid){
        String cid = service.selectOne(new EntityWrapper<UserGTCidEntity>().eq("uid",uid)).getCid();
        return cid;
    }

    /**
     * user_getui_cid表
     */
    @PostMapping("/inner/userGTCidEntity/insertOrUpdateGT")
    public Boolean insertOrUpdateGT(@RequestBody UserGTCidEntity userGTCidEntity){
        return service.insertOrUpdate(userGTCidEntity);
    }
}
