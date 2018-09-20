package cn.bitflash.service;

import cn.bitflash.entity.UserComplaintBean;
import cn.bitflash.entities.UserComplaintEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * 申诉
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
public interface UserComplaintService extends IService<UserComplaintEntity> {

    /**
     * @param id 申诉id
     * @return 申诉详情
     */
    UserComplaintBean getComplaintMessage(String id);

}
