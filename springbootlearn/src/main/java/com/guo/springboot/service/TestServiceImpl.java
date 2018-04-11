package com.guo.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public String getReturnWorld() {

        System.out.println("service current Thread:"  + Thread.currentThread().getName());

        return "hello world";
    }
}
