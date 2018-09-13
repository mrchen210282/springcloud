package cn.bitflash.vip.oauth2.controller;

import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.oauth2.feign.Oauth2Feign;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/external")
@Api(value = "对外接口")
public class Oauth2 {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Oauth2Feign oauth2Feign;

    /**
     * @return count 贝壳数量
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @GetMapping("getBKCNum")
    public R getBKCNum(HttpServletRequest request) throws UnsupportedEncodingException {
        String uid = request.getParameter("uid");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<String> inParam = new ArrayList<String>();
        inParam.add(uid);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);
        logger.info("time:" + time);
        logger.info("uid:" + uid);
        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(uid)) {
                UserAccountEntity accountEntity = oauth2Feign.selectAccountByUid(uid);
                if (null != accountEntity) {
                    String count = accountEntity.getAvailableAssets().toString();

                    Long timeVal = System.currentTimeMillis();
                    List<String> outParam = new ArrayList<String>();
                    outParam.add(count);
                    outParam.add(timeVal.toString());
                    outParam.add(apiKey);
                    String returnSign = Common.returnMD5(outParam);

                    return R.ok().put("availableAssets", count).put("code", 1).put("time", timeVal.toString()).put("sign", returnSign);
                } else {
                    return R.error().put("code", "500");
                }
            }
        }

        return R.error().put("code", "500");

    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    // @OtherLogin
    @GetMapping("changeBKCNum")
    public R changeBKCNum(HttpServletRequest request) throws UnsupportedEncodingException {
        // String uid = token.getUid();
        logger.info("---------------changeBKCNum----------------");
        String uid = request.getParameter("uid");
        String flag = request.getParameter("flag");
        String bkcNum = request.getParameter("bkcNum");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<String> inParam = new ArrayList<String>();
        inParam.add(uid);
        inParam.add(flag);
        inParam.add(bkcNum);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        logger.info("uid" + uid);
        logger.info("flag:" + flag);
        logger.info("bkcNum:" + bkcNum);
        logger.info("time:" + time);
        logger.info("sign:" + sign);

        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(bkcNum) && StringUtils.isNotBlank(flag)) {

                UserAccountEntity accountEntity = oauth2Feign.selectAccountByUid(uid);
                if (null != accountEntity) {
                    BigDecimal regulateIncome = new BigDecimal(bkcNum);
                    if (flag.equals("0")) {
                        // 加法
                        accountEntity.setRegulateIncome(accountEntity.getRegulateIncome().add(regulateIncome));
                    } else {
                        // 减法
                        if (accountEntity.getAvailableAssets().compareTo(regulateIncome) <= 0) {
                            return R.error().put("code", "500");
                        } else {
                            accountEntity.setRegulateIncome(accountEntity.getRegulateIncome().subtract(regulateIncome));
                        }
                    }
                    accountEntity.setAvailableAssets(accountEntity.getRegulateIncome().add(accountEntity.getRegulateRelease()));
                    oauth2Feign.updateAccountById(accountEntity);

                    Long timeVal = System.currentTimeMillis();
                    List<String> outParam = new ArrayList<String>();
                    outParam.add(accountEntity.getAvailableAssets().toString());
                    outParam.add(timeVal.toString());
                    outParam.add(apiKey);
                    String returnSign = Common.returnMD5(outParam);
                    return R.ok().put("code", 1).put("availableAssets", accountEntity.getAvailableAssets().toString()).put("time", timeVal).put("sign", returnSign);
                }
            }
        }
        return R.error().put("code", "500");
    }
}
