package com.guo.springboot.controller;

import com.guo.springboot.domain.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/fastjson")
public class FastjsonController {

    @RequestMapping("/profile")
    @ResponseBody
    public Profile getProfile() {
        Profile profile = new Profile();

        profile.setId(12);
        profile.setUsername("test");
        profile.setPassword("test");

        return profile;
    }
}
