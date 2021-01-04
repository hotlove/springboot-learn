package com.guo.springboot.decorator;

/**
 * @Date: 2021/1/4 10:01
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class CarDecorator implements Car{
    protected Car decoratorCar;

    // 关键的一步，在这里每次进行执行对象的切换decoratorCar
    public CarDecorator(Car decoratorCar) {
        this.decoratorCar = decoratorCar;
    }

//    public void run() {
//        System.out.println("付类");
//        decoratorCar.run();
//    }
}
