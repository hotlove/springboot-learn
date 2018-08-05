package com.guo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Enzo Cotter on 2018/6/26.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/login")
    public String index() {
        return "/index";
    }
}
