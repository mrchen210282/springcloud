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


    /**
     * getNumToPaging
     *
     * @return
     */
    @PostMapping("/inner/userBuy/getNumToPaging")
    Integer getNumToPaging();

    /**
     * showOrder
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userBuy/showOrder")
    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);


    /**
     * showOrderCount
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userBuy/showOrderCount")
    Integer showOrderCount(@RequestParam("uid") String uid);


    /**
     * deleteById
     *
     * @param id
     */
    @PostMapping("/inner/userBuy/deleteById")
    void deleteBuyById(@RequestParam("id")String id);

    /**
     * checkOrder
     *
     */
    @PostMapping("/inner/userBuy/checkOrder")
    UserBuyBean checkOrder(@RequestParam("id")String id);



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
