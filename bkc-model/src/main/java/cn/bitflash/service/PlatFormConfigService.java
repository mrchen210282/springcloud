package cn.bitflash.service;


import cn.bitflash.entities.PlatformConfigEntity;
import com.baomidou.mybatisplus.service.IService;

public interface PlatFormConfigService extends IService<PlatformConfigEntity> {

    String getVal(String key);

}
