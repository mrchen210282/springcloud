package cn.bitflash.controller;


import cn.bitflash.entities.VipConditions;
import cn.bitflash.service.VipConditonsService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VipConditionsController {

    @Autowired
    private VipConditonsService vipConditonsService;

    @PostMapping("/inner/VipConditions/selectVipConditonsByLevel")
    public List<VipConditions> selectVipConditonsByLevel(@RequestParam("level") String level){
        return vipConditonsService.selectList(new EntityWrapper<VipConditions>()
                .eq("level",level).or().eq("level",new Integer(level)+1));
    }
}
