package com.guo.springboot.design.facotry.abs;

import javax.sound.midi.Soundbank;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/26 18:37
 * @Description:
 */
public class BenchiCommonCar implements Car{
    @Override
    public void didi() {
        System.out.println("common benchi car didi");
    }

    @Override
    public void run() {
        System.out.println("common benchi car run");
    }

    @Override
    public void fly() {
        System.out.println("common benchi car fly");
    }
}
