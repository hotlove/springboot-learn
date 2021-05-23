package com.guo.springboot.design.facotry.abs;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:35
 * @Description:
 */
public interface AbstractCarFacotry {
    Car createCar(String type);
}
