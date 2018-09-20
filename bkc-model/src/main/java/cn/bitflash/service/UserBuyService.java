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
     * @return 除用户所有求购信息数量
     */
    Integer getNumToPaging();

    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);

    Integer showOrderCount(@RequestParam("uid") String uid);

    UserBuyBean checkOrder(@RequestParam("id")String id);


}
