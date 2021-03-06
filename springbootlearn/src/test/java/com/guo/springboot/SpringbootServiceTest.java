package com.guo.springboot;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringbootServiceTest {

    @Resource
    private ProfileService profileService;

    @Test
    public void testInsertProfile(){

        Profile profile = new Profile();
        profile.setId(8);
        profile.setMobile("13905607377");
        profile.setPassword("12345");
        profile.setUsername("123");
        profile.setAddress("fasdfasdf");

        try {
            profileService.insertProfile(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeWheel() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(100);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("test");
        }, 1000, 1000, TimeUnit.MILLISECONDS);

    }



}
