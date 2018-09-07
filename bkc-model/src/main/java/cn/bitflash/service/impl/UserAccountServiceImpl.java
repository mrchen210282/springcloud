package cn.bitflash.service.impl;

import cn.bitflash.entity.UserAccountBean;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.dao.UserAccountDao;
import cn.bitflash.service.UserAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author soso
 * @date 2018年5月21日 下午4:48:48
 */

@Service("userAccountService")
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDao, UserAccountEntity> implements UserAccountService {

    @Override
    public void updateUserAccountByParam(UserAccountEntity userAccount) {
        baseMapper.updateUserAccountByParam(userAccount);
    }

    public UserAccountBean selectUserAccount(Map<String, Object> map) {
        return baseMapper.selectUserAccount(map);
    }
}
