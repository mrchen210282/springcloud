package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.TokenEntity;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Api(value = "初始访问数据接口")
@FeignClient(value = "bkc-model")
public interface IndexFeign {

    /**
     * tb_user表
     */
    @ApiOperation(value = "根据手机号查询用户是否存在")
    @PostMapping("/inner/user/selectById")
    UserEntity selectUserEntityByMobile(@RequestParam("id")String mobile);

    @ApiOperation(value = "根据手机号删除用户信息")
    @PostMapping("/inner/user/deleteById")
    void delUserEntityBymMbile(@RequestParam("id")String mobile);

    @ApiOperation(value = "插入tb_user表")
    @PostMapping("/inner/user/insert")
    Boolean insertUserEntity(@RequestBody UserEntity userEntity);

    /**
     * tb_token表
     */
    @ApiOperation(value = "插入或者更新用户token值")
    @PostMapping("/inner/token/insertOrUpdateToken")
    Boolean insertOrUpdateToken(@RequestBody TokenEntity tokenEntity);

    /**
     * user_getui_cid表
     */
    @ApiOperation(value = "插入或者更新用户cid值")
    @PostMapping("/inner/userGTCidEntity/insertOrUpdateGT")
    Boolean insertOrUpdateGT(@RequestBody UserGTCidEntity userGTCidEntity);

    /**
     * user_account表
     */
    @ApiOperation(value = "插入user_account表")
    @PostMapping("/inner/userAccount/insertAccountIndex")
    Boolean insertAccount(@RequestParam("uid")String uid, @RequestParam("date")Date date);


    @ApiOperation(value = "根据uid删除user_account表")
    @PostMapping("/inner/userAccount/deleteById")
    void delAccountByUid(@RequestParam("id")String uid);

    /**
     * user_account_game表
     */
    @ApiOperation(value = "插入user_account_game表")
    @PostMapping("/inner/userAccountGame/insertGameIndex")
    Boolean insertGame(@RequestParam("uid")String uid, @RequestParam("date")Date date);

    @ApiOperation(value = "根据uid删除user_account_game表")
    @PostMapping("/inner/userAccountGame/deleteById")
    void delGameByUid(@RequestParam("id")String uid);

    /**
     * user_info表
     */
    @ApiOperation(value = "插入user_info表")
    @PostMapping("/inner/userInfouser/insertInfoIndex")
    Boolean insertInfo(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                       @RequestParam("isInvitation")boolean flag,@RequestParam("name")String name);

    @ApiOperation(value = "根据uid删除user_info表")
    @PostMapping("/inner/userInfo/deleteById")
    void delUserInfoByUid(@RequestParam("id")String uid);

    @PostMapping("/inner/userInfouser/insertInfoCode")
    Boolean insertInfoCode(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                           @RequestParam("isInvitation")boolean flag,@RequestParam("name")String name
            ,@RequestParam("code")String code);

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectCodeByCode")
    UserInvitationCodeEntity selectCodeByCode(@RequestParam("code")String code);
}
