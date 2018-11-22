package com.guo.springboot.controller;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/fastjson")
public class FastjsonController {

    @Resource
    private TestService testService;

    @RequestMapping("/profile")
    @ResponseBody
    public Profile getProfile() {
        Profile profile = new Profile();

        profile.setId(12);
        profile.setUsername("test");
        profile.setPassword("test");

        testService.testDefalutInterface();
        return profile;
    }
}
