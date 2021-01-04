package com.guo.springboot.decorator;

/**
 * @Date: 2021/1/4 10:04
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class FlyRunCarDecorator extends CarDecorator{
    public FlyRunCarDecorator(Car decoratorCar) {
        super(decoratorCar);
    }

    public void run() {
        decoratorCar.run();
        flyCarRun();
    }

    private void flyCarRun() {
        System.out.println("汽车开始起飞");
    }
}
