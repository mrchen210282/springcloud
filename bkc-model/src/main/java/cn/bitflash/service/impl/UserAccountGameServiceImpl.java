package cn.bitflash.service.impl;

import cn.bitflash.entity.UserAccountGameEntity;
import cn.bitflash.dao.UserAccountGameDao;
import cn.bitflash.service.UserAccountGameService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userAccountGameService")
public class UserAccountGameServiceImpl extends ServiceImpl<UserAccountGameDao, UserAccountGameEntity> implements UserAccountGameService {

}
