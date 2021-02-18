package com.guo.springboot.service;

import com.guo.springboot.domain.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public Profile getReturnWorld() throws InterruptedException {

        System.out.println("service current Thread:"  + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(200);
        Profile profile = new Profile();
        profile.setUsername("hello, world");
        profile.setId(1);
        profile.setAddress("xxx");
        return profile;
    }

    @Override
    public void helloword() {
        System.out.println("hello, world");
    }
}
