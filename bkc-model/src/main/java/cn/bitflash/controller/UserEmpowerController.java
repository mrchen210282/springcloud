package cn.bitflash.controller;


import cn.bitflash.entity.UserEmpowerEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserEmpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserEmpowerController {

    @Autowired
    private UserEmpowerService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserEmpowerEntity selectOne(Map<String, Object> param) {
        List<UserEmpowerEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserEmpowerEntity entity = entityList.get(0);
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
    public void updateById(UserEmpowerEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserEmpowerEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(int id) {
        service.deleteById(id);
    }

}
