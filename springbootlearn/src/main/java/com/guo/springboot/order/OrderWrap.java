package com.guo.springboot.order;

/**
 * @Date: 2020/8/28 16:48
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class OrderWrap extends WrapHandlerManager{
    @Override
    public WrapHandlerManager setContext(Object ctx) {
        this.context = ctx;
        return this;
    }

    @Override
    public WrapHandlerManager addLast(WrapHandler wrapHandler) {

        return this;
    }
}
