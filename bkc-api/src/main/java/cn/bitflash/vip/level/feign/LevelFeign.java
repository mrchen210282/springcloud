package cn.bitflash.vip.level.feign;

import cn.bitflash.entity.*;
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
    @PostMapping("")
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);
    @PostMapping("")
    Boolean updateAccountById(@RequestBody UserAccountEntity account);

    /**
     * user_info 表
     */
    @PostMapping("")
    UserInfoEntity selectUserInfoByUid(@RequestParam("uid")String uid);

    @PostMapping("")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    /**
     * user_relation 表
     */
    @PostMapping("")
    UserRelationEntity selectRelationByCode(@RequestParam("code")String code);

    @PostMapping("")
    List<UserRelationJoinAccountEntity>  selectTreeNodes(@RequestParam("uid")String uid);

    /**
     * user_invitation_code 表
     */
    @PostMapping("")
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("uid")String uid);

    @PostMapping("")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code")String code);
    @PostMapping("")
    Boolean insertInvitationCode(@RequestBody UserInvitationCodeEntity code);
    @PostMapping("")
    Boolean insertTreeNode(@RequestParam("pid")String pid,@RequestParam("uid") String uid,@RequestParam("code")String code);

    /**
     * platform_config 表
     */
    @PostMapping("")
    String getVal(@RequestParam("key")String key);

    /**
     * user_info_config表
     */
    @PostMapping("")
    UserInfoConfigEntity selectInfoConfigById(@RequestParam("vip")Integer vip);
}
