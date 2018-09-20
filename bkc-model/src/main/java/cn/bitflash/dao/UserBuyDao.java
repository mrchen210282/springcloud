package cn.bitflash.dao;

import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entities.UserBuyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 求购
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
public interface UserBuyDao extends BaseMapper<UserBuyEntity> {

    Integer showBuyingCount(String uid);

    List<UserBuyBean> showBuying(@Param("uid") String uid, @Param("pages") Integer pages);

    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);

    Integer showOrderCount(@RequestParam("uid") String uid);

    UserBuyBean checkOrder(@RequestParam("id")String id);
}
