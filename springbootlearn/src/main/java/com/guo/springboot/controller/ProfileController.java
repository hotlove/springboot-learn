package com.guo.springboot.controller;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.ProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    private ProfileService profileService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public String insertProfile() throws Exception {
        Profile profile = new Profile();
        profile.setId(2);
        profile.setPassword("12345");
        profile.setUsername("123");
        profile.setAddress("fasdfasdf");

        profileService.insertProfile(profile);

        return "hello profile";
    }
}
