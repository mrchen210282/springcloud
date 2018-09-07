package cn.bitflash.service;

import cn.bitflash.entity.TradePoundageEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

public interface TradePoundageService extends IService<TradePoundageEntity> {

    void deleteTradePoundageById(Map<String, Object> map);
}
