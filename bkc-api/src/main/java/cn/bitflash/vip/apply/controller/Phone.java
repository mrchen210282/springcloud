package cn.bitflash.vip.apply.controller;

import cn.bitflash.vip.apply.feign.PhoneFeign;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Phone {

    @Autowired
    private PhoneFeign phoneFeign;


    public static void main(String[] args) {
        List<String> list2 = new ArrayList<>();
        String code = "sds";
        List<String> list1 = new ArrayList<>();

        String str = null;

        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).equals(code)) {
                list1.add(list2.get(i));
                code = list2.get(i);
            }
        } return;

    }
}
