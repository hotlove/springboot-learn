package com.guo.springboot.order;

/**
 * @Date: 2020/8/28 15:58
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class AbstractWrapContext implements WrapHandler{

    AbstractWrapContext prev;

    AbstractWrapContext next;

    WrapHandler wrapHandler;

    abstract void hireNext(AbstractWrapContext ctx);

    private Object exteranlData;

    public Object getExteranlData() {
        return exteranlData;
    }

    public void setExteranlData(Object exteranlData) {
        this.exteranlData = exteranlData;
    }
}
