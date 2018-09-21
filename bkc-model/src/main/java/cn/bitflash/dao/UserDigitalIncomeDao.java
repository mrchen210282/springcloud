package cn.bitflash.dao;

import cn.bitflash.entities.UserDigitalIncomeEntity;
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
public interface UserDigitalIncomeDao extends BaseMapper<UserDigitalIncomeEntity> {

    void updateUserAccountByParam(UserDigitalIncomeEntity userDigitalIncomeEntity);

}
