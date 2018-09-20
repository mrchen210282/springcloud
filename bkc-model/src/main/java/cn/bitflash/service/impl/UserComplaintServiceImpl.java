package cn.bitflash.service.impl;

import cn.bitflash.entity.UserComplaintBean;
import cn.bitflash.entities.UserComplaintEntity;
import cn.bitflash.dao.UserComplaintDao;
import cn.bitflash.service.UserComplaintService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 申诉
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
@Service("userComplaintService")
public class UserComplaintServiceImpl extends ServiceImpl<UserComplaintDao, UserComplaintEntity> implements UserComplaintService {

    @Override
    public UserComplaintBean getComplaintMessage(String id) {
        return baseMapper.getComplaintMessage(id);
    }

}
