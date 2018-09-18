package cn.bitflash.vip.verify.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.util.R;
import cn.bitflash.vip.verify.feign.VerifyFeign;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("verify")
@Api(value = "验证Con", tags = {"验证权限"})
public class ValidateInfo {

    @Autowired
    private VerifyFeign verifyFeign;

    @Login
    @PostMapping("validatePwd")
    public R validatePwd(@RequestAttribute(ApiLoginInterceptor.UID) String uid, @RequestParam(value = "key", required = false) String key) {
        if (key == null) {
            UserInfoEntity infoEntity = verifyFeign.selectUserInfoByUid(uid);
            //未进行实名认证
            if (infoEntity.getIsAuthentication().equals("0") || infoEntity.getIsAuthentication().equals("-1")) {
                return R.ok().put("msg", "3");
            }
            //实名认证中
            if (infoEntity.getIsAuthentication().equals("1")) {
                return R.ok().put("msg", "4");
            }
        }
        UserPayPwdEntity payPwdEntity = verifyFeign.selectUserPwdByUid(uid);
        if (payPwdEntity == null || StringUtil.isNullOrEmpty(payPwdEntity.getPayPassword())) {
            //如果没有交易密码
            return R.ok().put("msg", "1");
        }
        List<UserPayUrlEntity> urlEntity = verifyFeign.selectUserUrlList(uid);
        if (urlEntity.size() == 0) {
            //未上传收款码
            return R.ok().put("msg", "2");
        }

        return R.ok().put("msg", "0");
    }
}
