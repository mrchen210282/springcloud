package cn.bitflash.dao;


import cn.bitflash.entity.TradePoundageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

public interface TradePoundageDao extends BaseMapper<TradePoundageEntity> {

    void deleteTradePoundageById(Map<String, Object> map);
}
