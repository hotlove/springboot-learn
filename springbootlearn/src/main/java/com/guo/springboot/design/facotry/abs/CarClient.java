package com.guo.springboot.design.facotry.abs;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:41
 * @Description:
 */
public class CarClient {
    public static void main(String[] args) {
        OrderCar orderCar = new OrderCar(new BenchiCarFactory());
        Car car = orderCar.getCar("common");
        car.didi();
        car.fly();
        car.run();
        
    }
}
