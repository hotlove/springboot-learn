package com.guo.springboot.design.facotry.abs;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:40
 * @Description:
 */
public class OrderCar {

    private AbstractCarFacotry facotry;

    public OrderCar(AbstractCarFacotry facotry) {
        this.facotry = facotry;
    }

    public Car getCar(String type) {
        return facotry.createCar(type);
    }
}
