package cn.bitflash.service.impl;


import cn.bitflash.dao.VipConditonsDao;
import cn.bitflash.entities.VipConditions;
import cn.bitflash.service.VipConditonsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("vipConditonsService")
public class VipConditonsServiceImpl extends ServiceImpl<VipConditonsDao, VipConditions> implements VipConditonsService {


}
