package com.guo.springboot.decorator;

/**
 * @Date: 2021/1/4 10:05
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class DecoratorMain {
    public static void main(String[] args) {
        Car car = new BMWCar();
        Car autoCar = new AutoRunCarDecorator(car);
        Car flyCar = new FlyRunCarDecorator(new AutoRunCarDecorator(car));

        autoCar.run();
        flyCar.run();
    }
}
