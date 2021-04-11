package com.guo.springboot;

import com.guo.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {


    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String helloWrold() throws InterruptedException {

        System.out.println("current Thread:" + Thread.currentThread().getName());

        return testService.getReturnWorld().getUsername();
    }

    @GetMapping("/hello2")
    public  String helloWorld2() {
        return "hello world2";
    }
}
