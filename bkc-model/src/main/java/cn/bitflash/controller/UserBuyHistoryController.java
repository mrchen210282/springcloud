package cn.bitflash.controller;


import cn.bitflash.entity.UserBuyHistoryBean;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBuyHistoryService;
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
public class UserBuyHistoryController {

    @Autowired
    private UserBuyHistoryService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserBuyHistoryEntity selectOne(Map<String, Object> param) {
        List<UserBuyHistoryEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserBuyHistoryEntity entity = entityList.get(0);
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
    public void updateById(UserBuyHistoryEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserBuyHistoryEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        service.deleteById(id);
    }

    /**
     * selectBuyHistory
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public UserBuyHistoryBean selectBuyHistory(String id) {
        UserBuyHistoryBean userBuyHistoryBean = service.selectBuyHistory(id);
        return userBuyHistoryBean;
    }

}
