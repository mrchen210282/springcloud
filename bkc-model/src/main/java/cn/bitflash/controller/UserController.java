package cn.bitflash.controller;


import cn.bitflash.entity.LoginForm;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserEntity selectOne(Map<String, Object> param) {
        List<UserEntity> entityList = userService.selectByMap(param);
        if (entityList.size() > 0) {
            UserEntity entity = entityList.get(0);
            return entity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserEntity entity) {
        userService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    @PostMapping("insert")
    public void insert(@RequestBody JSONObject object) {
        System.out.println(object.getString("uid"));
    }

    /**
     * deleteById
     * @param mobile String
     * @return null
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    @PostMapping("deleteById")
    public void deleteById(@RequestParam("mobile")String mobile) {
        userService.deleteById(mobile);
    }

    /**
     * queryByMobile
     * @param mobile String
     * @return uid：value, mobile：value, password：value
     */
    @PostMapping("queryByMobile")
    public UserEntity queryByMobile(@RequestParam("mobile")String mobile) {
        UserEntity userEntity = userService.queryByMobile(mobile);
        return userEntity;
    }

  /*  *//**
     * login
     *
     * @return
     *//*
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Map<String, Object> login(LoginForm form) {
        Map<String, Object> map = userService.login(form);
        return map;
    }*/

}
