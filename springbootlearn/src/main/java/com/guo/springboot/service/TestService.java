package com.guo.springboot.service;

public interface TestService {

    String getReturnWorld();

    void helloword();

    default void testDefalutInterface() {
        this.helloword();
    }
}
