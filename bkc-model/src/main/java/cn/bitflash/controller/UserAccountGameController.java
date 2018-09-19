package cn.bitflash.controller;


import cn.bitflash.service.UserAccountGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserAccountGameController {

    @Autowired
    private UserAccountGameService service;

}
