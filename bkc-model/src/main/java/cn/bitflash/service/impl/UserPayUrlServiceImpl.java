package cn.bitflash.service.impl;

import cn.bitflash.entities.UserPayImgEntity;
import cn.bitflash.dao.UserPayUrlDao;
import cn.bitflash.service.UserPayUrlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userPayUrlService")
public class UserPayUrlServiceImpl extends ServiceImpl<UserPayUrlDao, UserPayImgEntity> implements UserPayUrlService {

}
