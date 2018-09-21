package cn.bitflash.vip.level.feign;

import cn.bitflash.entities.*;
import cn.bitflash.entity.UserVipConditionsEntity;
import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface LevelFeign {

    /**
     * user_account 表
     */
    @PostMapping("/inner/userAccount/selectById")
    UserDigitalIncome selectAccountByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userAccount/updateById")
    Boolean updateAccountById(@RequestBody UserDigitalIncome account);

    /**
     * user_info 表
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserInfoByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userInfo/updateById")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    /**
     * user_relation 表
     */
    @PostMapping("/inner/userRelation/selectRelationByCode")
    UserRelationEntity selectRelationByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userRelation/selectTreeNodes")
    List<UserRelationJoinAccountEntity> selectTreeNodes(@RequestParam("uid") String uid);

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userInvitationCode/selectCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userInvitationCode/insert")
    Boolean insertInvitationCode(@RequestBody UserInvitationCodeEntity code);

    @PostMapping("/inner/userRelation/insertTreeNode")
    Boolean insertTreeNode(@RequestParam("pid") String pid, @RequestParam("uid") String uid, @RequestParam("code") String code);

    /**
     * platform_config 表
     */
    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key") String key);

    /**
     * user_info_config表
     */
    @PostMapping("/inner/userInfoConfig/selectById")
    UserInfoConfigEntity selectInfoConfigById(@RequestParam("vip") Integer vip);

    /**
     * vip_conditions 表
     */
    List<UserVipConditionsEntity> selectVipConditonsByLevel(@RequestParam("level") String level);

    /**
     * user_cash_income 表
     */
    UserCashIncome selectUserCashIncomeByUid(@RequestParam("uid") String uid);

    Boolean updateUserCashIncomeById(@RequestBody UserCashIncome cashIncome);
}
