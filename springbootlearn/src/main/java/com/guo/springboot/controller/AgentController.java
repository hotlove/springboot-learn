package com.guo.springboot.controller;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date: 2021/2/8 9:04
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@RestController
public class AgentController {
    @Resource
    TestService testService;

    @GetMapping("/testi")
    @ResponseBody
    public String test() throws InterruptedException {
        System.out.println("test controller");
        Profile returnWorld = testService.getReturnWorld();
        return returnWorld.getUsername();
    }
}
