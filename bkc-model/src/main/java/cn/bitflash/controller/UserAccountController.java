package cn.bitflash.controller;


import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserAccountService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userAccount/selectById")
    public JSONObject selectById(@RequestParam("id") String id) {
        UserAccountEntity entity = service.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("purchase", entity.getPurchase());
        jsonObject.put("giveAmount", entity.getGiveAmount());
        jsonObject.put("totelAssets", entity.getTotelAssets());
        jsonObject.put("regulateRelease", entity.getRegulateRelease());
        jsonObject.put("regulateIncome", entity.getRegulateIncome());
        jsonObject.put("frozenAssets", entity.getFrozenAssets());
        jsonObject.put("availableAssets", entity.getAvailableAssets());
        jsonObject.put("lftAchievement", entity.getLftAchievement());
        jsonObject.put("rgtAchievement", entity.getRgtAchievement());
        jsonObject.put("totelIncome", entity.getTotelIncome());
        jsonObject.put("dailyIncome", entity.getDailyIncome());
        jsonObject.put("createTime", entity.getCreateTime());
        return jsonObject;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userAccount/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setUid(json.getString("uid"));
        entity.setPurchase(json.getBigDecimal("purchase"));
        entity.setGiveAmount(json.getBigDecimal("giveAmount"));
        entity.setTotelAssets(json.getBigDecimal("totelAssets"));
        entity.setRegulateRelease(json.getBigDecimal("regulateRelease"));
        entity.setRegulateIncome(json.getBigDecimal("regulateIncome"));
        entity.setFrozenAssets(json.getBigDecimal("frozenAssets"));
        entity.setAvailableAssets(json.getBigDecimal("availableAssets"));
        entity.setLftAchievement(json.getBigDecimal("lftAchievement"));
        entity.setRgtAchievement(json.getBigDecimal("rgtAchievement"));
        entity.setTotelIncome(json.getBigDecimal("totelIncome"));
        entity.setDailyIncome(json.getBigDecimal("dailyIncome"));
        entity.setCreateTime(json.getDate("createTime"));
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userAccount/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setUid(json.getString("uid"));
        entity.setPurchase(json.getBigDecimal("purchase"));
        entity.setGiveAmount(json.getBigDecimal("giveAmount"));
        entity.setTotelAssets(json.getBigDecimal("totelAssets"));
        entity.setRegulateRelease(json.getBigDecimal("regulateRelease"));
        entity.setRegulateIncome(json.getBigDecimal("regulateIncome"));
        entity.setFrozenAssets(json.getBigDecimal("frozenAssets"));
        entity.setAvailableAssets(json.getBigDecimal("availableAssets"));
        entity.setLftAchievement(json.getBigDecimal("lftAchievement"));
        entity.setRgtAchievement(json.getBigDecimal("rgtAchievement"));
        entity.setTotelIncome(json.getBigDecimal("totelIncome"));
        entity.setDailyIncome(json.getBigDecimal("dailyIncome"));
        entity.setCreateTime(json.getDate("createTime"));
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userAccount/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
    }
}
