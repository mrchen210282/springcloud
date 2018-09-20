package cn.bitflash.service.impl;

import cn.bitflash.dao.BuyPoundageDao;
import cn.bitflash.entities.BuyPoundageEntity;
import cn.bitflash.service.BuyPoundageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("buyPoundageService")
public class BuyPoundageServiceImpl extends ServiceImpl<BuyPoundageDao, BuyPoundageEntity> implements BuyPoundageService {

}
