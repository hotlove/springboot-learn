package com.guo.springboot.service;


import com.guo.springboot.domain.Profile;

public interface TestService {

    Profile getReturnWorld() throws InterruptedException;

    void helloword();

    default void testDefalutInterface() {
        this.helloword();
    }
}
