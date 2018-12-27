package com.esp.server.userserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName: user-service
 * @Auther: GERRY
 * @Date: 2018/11/13 16:04
 * @Description:
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }
}
