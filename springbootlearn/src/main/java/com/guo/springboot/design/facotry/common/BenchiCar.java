package com.guo.springboot.design.facotry.common;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:20
 * @Description:
 */
public class BenchiCar implements Car{
    @Override
    public void didi() {
        System.out.println("奔驰滴滴");
    }

    @Override
    public void run() {
        System.out.println("奔驰运行");
    }

    @Override
    public void fly() {
        System.out.println("奔驰飞翔");
    }
}
