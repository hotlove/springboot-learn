package com.guo.springboot.decorator;

/**
 * @Date: 2021/1/4 10:03
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class AutoRunCarDecorator extends CarDecorator{
    // 这个必须在，在这里进行执行对象的切换
    public AutoRunCarDecorator(Car decoratorCar) {
        super(decoratorCar);
    }

    public void run() {
        decoratorCar.run();
        autoRun();
    }

    private void autoRun() {
        System.out.println("开启自动驾驶");
    }
}
