package com.guo.springboot.decorator;

/**
 * @Date: 2021/1/4 10:01
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class BMWCar implements Car{
    @Override
    public void run() {
        System.out.println("宝马车启动了");
    }
}
