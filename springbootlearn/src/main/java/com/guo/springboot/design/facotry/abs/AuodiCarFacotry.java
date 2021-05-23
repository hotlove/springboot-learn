package com.guo.springboot.design.facotry.abs;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:35
 * @Description:
 */
public class AuodiCarFacotry implements AbstractCarFacotry{
    @Override
    public Car createCar(String type) {
        if (type.equals("common")) {
            return new AuodiCommonCar();
        } else if (type.equals("auto")){
            return new AuodiAutoCar();
        }
        return null;
    }
}
