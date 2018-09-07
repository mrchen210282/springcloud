package cn.bitflash.service;

import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyMessageBean;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 求购
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
public interface UserBuyService extends IService<UserBuyEntity> {

    /**
     * @param uid   用户id
     * @param pages 分页
     * @return 除用户所有求购信息
     */
    List<UserBuyMessageBean> getBuyMessage(String uid, Integer pages);

    /**
     * @return 除用户所有求购信息数量
     */
    Integer getNumToPaging();

    /**
     * @param uid   用户id
     * @param pages 分页
     * @return 用户所有求购信息
     */
    List<UserBuyBean> selectBuyList(String uid, Integer pages);

    /**
     * @param uid 用户id
     * @return 用户所有求购信息数量
     */
    Integer selectUserBuyOwnCount(String uid);

    /**
     * @param uid   用户id
     * @param pages 分页
     * @return 用户申诉记录
     */
    List<UserBuyBean> selectAppealList(String uid,Integer pages);

    /**
     * @param uid 用户id
     * @return 用户申诉记录数量
     */
    Integer selectAppealCount(String uid);

}
