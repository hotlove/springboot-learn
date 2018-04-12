package com.guo.springboot;

import com.guo.springboot.domain.Profile;
import com.guo.springboot.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {

    @Resource
    private ProfileService profileService;

    @Test
    public void testInsertProfile() {

        Profile profile = new Profile();
        profile.setId(1);
        profile.setPassword("12345");
        profile.setUsername("123");
        profile.setAddress("fasdfasdf");

        profileService.insertProfile(profile);
    }


}
