package cn.bitflash.service.impl;

import cn.bitflash.entities.UserBrokerageEntity;
import cn.bitflash.dao.UserBrokerageDao;
import cn.bitflash.service.UserBrokerageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @version 2018年7月3日上午11:02:19
 */
@Service("userBrokerageService")
public class UserBrokerageServiceImpl extends ServiceImpl<UserBrokerageDao, UserBrokerageEntity> implements UserBrokerageService {

}
