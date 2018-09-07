package cn.bitflash.dao;


import cn.bitflash.entity.UserComplaintBean;
import cn.bitflash.entity.UserComplaintEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 申诉
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
public interface UserComplaintDao extends BaseMapper<UserComplaintEntity> {
    UserComplaintBean getComplaintMessage(String id);
}