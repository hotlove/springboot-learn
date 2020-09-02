package com.guo.springboot.order;

/**
 * @Date: 2020/9/1 20:04
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class SimpleWrapHandler<T> implements WrapHandler{

    @Override
    public void handler(AbstractWrapContext ctx) {
        this.handler0((T) ctx);
    }

    public abstract void handler0(T ctx);

}
