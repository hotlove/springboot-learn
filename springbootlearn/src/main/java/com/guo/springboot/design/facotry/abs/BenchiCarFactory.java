package com.guo.springboot.design.facotry.abs;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:35
 * @Description:
 */
public class BenchiCarFactory implements AbstractCarFacotry{
    @Override
    public Car createCar(String type) {
        if (type.equals("common")) {
            return new BenchiCommonCar();
        } else if (type.equals("auto")) {
            return new BenchiAutoCar();
        }
        return null;
    }
}
