package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.TradePoundageEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface TradePoundageFeign {

    @PostMapping("/inner/tradePoundage/insert")
    void insert(TradePoundageEntity entity);

    @PostMapping("/inner/tradePoundage/selectOne")
    TradePoundageEntity selectOne(Map<String, Object> param);

    @PostMapping("/inner/tradePoundage/deleteById")
    void deleteById(String id);
}
