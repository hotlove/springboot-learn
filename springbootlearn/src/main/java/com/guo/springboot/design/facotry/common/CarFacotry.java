package com.guo.springboot.design.facotry.common;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:22
 * @Description:
 */
public abstract class CarFacotry {

    abstract Car createCar();

    Car getCar(String type) {
        return createCar();
    }

}
