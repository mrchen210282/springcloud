package cn.bitflash.service.impl;

import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.dao.TradePoundageDao;
import cn.bitflash.service.TradePoundageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("tradePoundageService")
public class TradePoundageServiceImpl extends ServiceImpl<TradePoundageDao, TradePoundageEntity> implements TradePoundageService {

    public void deleteTradePoundageById(Map<String, Object> map) {
        baseMapper.deleteTradePoundageById(map);
    }
}
