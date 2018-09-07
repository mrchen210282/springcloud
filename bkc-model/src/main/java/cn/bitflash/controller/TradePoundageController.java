package cn.bitflash.controller;


import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.TradePoundageService;
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
public class TradePoundageController {

    @Autowired
    private TradePoundageService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public TradePoundageEntity selectOne(Map<String, Object> param) {
        List<TradePoundageEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            TradePoundageEntity entity = entityList.get(0);
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
    public void updateById(TradePoundageEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(TradePoundageEntity entity) {
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
     * deleteTradePoundageById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteTradePoundageById(Map<String, Object> map) {
        service.deleteTradePoundageById(map);
    }

}
