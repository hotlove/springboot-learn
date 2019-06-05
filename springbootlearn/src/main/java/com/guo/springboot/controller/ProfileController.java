package com.guo.springboot.controller;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.ProfileService;
import com.guo.springboot.zookeeper.ZookeeperFactory;
import com.guo.springboot.zookeeper.ZookeeperLearn;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
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

    @RequestMapping(value = "/zk")
    @ResponseBody
    public void zk() throws KeeperException, InterruptedException {
//        ZooKeeper zooKeeper = ZookeeperFactory.getInstace().getZookeeperClient();
//        byte [] data = zooKeeper.getData("/cloud-config", true, new Stat());
//        System.out.println(new String(data));
    }
}
