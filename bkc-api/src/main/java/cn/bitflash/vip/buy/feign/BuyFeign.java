package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "bkc-model")
public interface BuyFeign {

//-------------------------------------------------------usreBuy--------------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/usreBuy/insert")
    void insertBuy(@RequestBody UserBuyEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/usreBuy/selectById")
    UserBuyEntity selectBuyById(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/usreBuy/updateById")
    void updateBuyById(@RequestBody UserBuyEntity entity);

    /**
     * showBuying
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userBuy/showBuying")
    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);
//    select
//    ub.* ,ui.nickname as nickname
//    from user_buy ub left join user_info ui on ub.purchase_uid=ui.uid
//    where ub.purchase_uid!=#{uid} and purchase_state ='1'
//    order by ub.price desc
//    limit #{pages},6

    /**
     * getNumToPaging
     *
     * @return
     */
    @PostMapping("/inner/userBuy/getNumToPaging")
    Integer getNumToPaging();

    /**
     * selectOrder
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userBuy/selectOrder")
    List<UserBuyBean> selectOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);
//    select
//    ub.* , ui.nickname as nickname
//    from user_buy ub left join user_info ui on ub.purchase_uid=ui.uid
//    WHERE
//    ub.purchase_uid = #{uid}
//    and ub.state != '0' and ub.state != '6' and ub.state != '9'
//    or
//    ub.sell_uid = #{uid}
//    limit #{pages},6

    /**
     * showOrderCount
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userBuy/showOrderCount")
    Integer showOrderCount(@RequestParam("uid") String uid);
//    SELECT count(0)
//    FROM user_buy ub
//    WHERE
//    ub.purchase_uid = #{uid}
//    and ub.state != '0' and ub.state != '6' and ub.state != '9'
//    or
//    ub.sell_uid = #{uid}

    /**
     * deleteById
     *
     * @param id
     */
    @PostMapping("/inner/userBuy/deleteById")
    void deleteBuyById(@RequestParam("id")String id);

    /**
     * selectOrderCheck
     *
     */
    @PostMapping("/inner/userBuy/selectOrderCheck")
    UserBuyBean selectOrderCheck(@RequestParam("id")String id);

//    SELECT
//    ub.*,
//    ( SELECT ui.nickname
//    FROM user_info ui
//    WHERE
//    uh.purchase_uid=ui.uid
//	   ) AS purchaseNickname,
//	  ( SELECT ui.nickname
//    FROM user_info ui
//    WHERE
//    ub.sell_uid=ui.uid
//	  ) AS sellNickname,
//	  ( SELECT ui.mobile
//    FROM user_info ui
//    WHERE
//    uh.purchase_uid=ui.uid
//	  ) AS purMobile,
//	  ( SELECT ui.mobile
//    FROM user_info ui
//    WHERE
//    uh.sell_uid=ui.uid
//		) AS sellMobile
//    FROM user_buy ub
//    where ub.id = #{id}

//-------------------------------------------------------userBuyHistory-------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/userBuyHistory/insert")
    void insertHistory(@RequestBody UserBuyHistoryEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(@RequestParam("id") String id);

    /**
     * selectBuyHistory
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectBuyHistory")
    UserBuyHistoryBean selectBuyHistory(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBuyHistory/updateById")
    void updateHistoryById(@RequestBody UserBuyHistoryEntity entity);

    /**
     * deleteById
     *
     * @param id
     */
    @PostMapping("/inner/userBuyHistory/deleteById")
    void deleteHistoryById(@RequestParam("id")String id);





//-------------------------------------------------------userComplaint-------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/userComplaint/insert")
    void insertComplaint(@RequestBody UserComplaintEntity entity);


//-------------------------------------------------------buyPoundage-------------------------------------------------

    /**
     *insert
     *
     * @param entity
     */
    @PostMapping("/inner/buyPoundage/insert")
    void insertPoundage(@RequestBody BuyPoundageEntity entity);

    /**
     *deletePoundage
     *
     */
    @PostMapping("/inner/buyPoundage/deletePoundage")
    void deletePoundage(@RequestParam("id")String id);

    /**
     *selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/buyPoundage/selectById")
    BuyPoundageEntity selectPoundageById(@RequestParam("id")String id);


//-------------------------------------------------------userAccount-------------------------------------------------

    /**
     *selectById
     * @param id
     * @return
     */
    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(@RequestParam("id") String id);

    /**
     *updateAccountById
     * @param entity
     */
    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(@RequestBody UserAccountEntity entity);


//-------------------------------------------------------userBrokerage-------------------------------------------------

    /**
     *updateById
     * @param entity
     */
    @PostMapping("/inner/userBrokerage/updateById")
    void updateBrokerageById(@RequestBody UserBrokerageEntity entity);

    /**
     *selectOne
     * @param id
     * @return
     */
    @PostMapping("/inner/userBrokerage/selectOne")
    UserBrokerageEntity selectBrokerageById(@RequestParam("id") int id);


//-------------------------------------------------------FINISH-------------------------------------------------
    /**
     *selectUid
     * @param uid
     * @return
     */
    @PostMapping("inner/userPayPwd/selectUid")
    UserPayPwdEntity selectUid(@RequestParam("uid") String uid);

    /**
     *getVal
     * @param key
     * @return
     */
    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key")String key);

    /**
     *selectCid
     * @param param
     * @return
     */
    @PostMapping("/inner/userGTCidEntity/selectOne")
    UserGTCidEntity selectCid(Map<String, Object> param);


    /**
     *
     */
    @PostMapping("/inner/userTradeConfig/selectOne")
    UserTradeConfigEntity selectConfig(@RequestParam("remark")String remark);
}
