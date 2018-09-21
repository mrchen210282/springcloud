package cn.bitflash.controller;


import cn.bitflash.entities.UserCashIncome;
import cn.bitflash.service.UserCashIncomeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserCashIncomeController {

    @Autowired
    private UserCashIncomeService userCashIncomeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userCashIncome/selectById")
    public UserCashIncome selectById(@RequestParam("id") String id) {
        UserCashIncome entity = userCashIncomeService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userCashIncome/updateById")
    public void updateById(@RequestBody JSONObject json) {
        UserCashIncome entity = new UserCashIncome();
        entity.setUid(json.getString("uid"));
        entity.setTotalIncome(json.getBigDecimal("totelIncome"));
        entity.setDailyIncome(json.getBigDecimal("dailyIncome"));
        entity.setStageHead(json.getString("stageHead"));
        entity.setShareBenefit(json.getString("shareBenefit"));
        entity.setPower(json.getBigDecimal("power"));
        entity.setTotalIncome(json.getBigDecimal("totalIncome"));
        entity.setLineF(json.getString("lineF"));
        entity.setLineS(json.getString("lineS"));
        entity.setLineT(json.getString("lineT"));
        userCashIncomeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userCashIncome/insert")
    public void insert(@RequestBody JSONObject json) {
        UserCashIncome entity = new UserCashIncome();
        entity.setUid(json.getString("uid"));
        entity.setTotalIncome(json.getBigDecimal("totelIncome"));
        entity.setDailyIncome(json.getBigDecimal("dailyIncome"));
        entity.setStageHead(json.getString("stageHead"));
        entity.setShareBenefit(json.getString("shareBenefit"));
        entity.setPower(json.getBigDecimal("power"));
        entity.setTotalIncome(json.getBigDecimal("totalIncome"));
        entity.setLineF(json.getString("lineF"));
        entity.setLineS(json.getString("lineS"));
        entity.setLineT(json.getString("lineT"));
        userCashIncomeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userCashIncome/deleteById")
    public void deleteById(@RequestParam("id") String id) {
        userCashIncomeService.deleteById(id);
    }

}
