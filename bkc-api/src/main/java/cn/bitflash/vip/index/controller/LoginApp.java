package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entities.TokenEntity;
import cn.bitflash.entities.UserEntity;
import cn.bitflash.entities.UserGTCidEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.util.AESTokenUtil;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import cn.bitflash.util.ValidatorUtils;
import cn.bitflash.vip.index.entity.LoginForm;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/index")
@Api(value = "登录Con",tags={"用户app登录"})
public class LoginApp {

    @Autowired
    private IndexFeign indexFeign;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "mobile",dataType = "String"),
            @ApiImplicitParam(value = "password",dataType = "password"),
            @ApiImplicitParam(value = "cid",dataType = "cid")
    })
    public R login(@RequestBody LoginForm form){
        // 表单校验
        ValidatorUtils.validateEntity(form);
        UserEntity user = indexFeign.selectUserEntityByMobile(form.getMobile());

        // 密码错误
        if (user == null || !user.getPassword().equals(form.getPassword())) {
            throw new RRException("手机号或密码错误" );
        }

        // 插入token
        String token = generateToken();
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUid(user.getUid());
        tokenEntity.setMobile(user.getMobile());
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(new Date());
        indexFeign.insertOrUpdateToken(tokenEntity);

        //插入cid
        UserGTCidEntity userGTCidEntity = new UserGTCidEntity();
        userGTCidEntity.setCid(form.getCid());
        userGTCidEntity.setUid(user.getUid());
        userGTCidEntity.setUpdateTime(new Date());
        indexFeign.insertOrUpdateGT(userGTCidEntity);

        //加入缓存，失效时间15天
        redisUtils.set(tokenEntity.getToken(),tokenEntity,60*60*24*15);

        Map<String, Object> map = new HashMap<>(2);
        String time = System.currentTimeMillis()+"bkc";
        map.put("token", AESTokenUtil.setToken(time,tokenEntity.getToken()));
        map.put("expire", time);
        return R.ok(map);
    }

    @Login
    @PostMapping("logout" )
    public R logout(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        redisUtils.delete(uid);
        return R.ok();
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "" );
    }
}
