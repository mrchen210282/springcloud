package cn.bitflash.dao;

import cn.bitflash.entity.UserAccountBean;
import cn.bitflash.entity.UserAccountEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author soso
 * @date 2018年5月21日 下午4:45:51
 */
@Repository
public interface UserAccountDao extends BaseMapper<UserAccountEntity> {

    void updateUserAccountByParam(UserAccountEntity userAccountEntity);

    UserAccountBean selectUserAccount(Map<String, Object> map);

}
