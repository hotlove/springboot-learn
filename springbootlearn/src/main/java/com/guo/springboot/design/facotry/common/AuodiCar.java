package com.guo.springboot.design.facotry.common;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:21
 * @Description:
 */
public class AuodiCar implements Car{
    @Override
    public void didi() {
        System.out.println("auodi didi");
    }

    @Override
    public void run() {
        System.out.println("auodi run");
    }

    @Override
    public void fly() {
        System.out.println("auodi fly");
    }
}
