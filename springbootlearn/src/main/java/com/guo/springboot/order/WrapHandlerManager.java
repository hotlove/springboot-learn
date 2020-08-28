package com.guo.springboot.order;

/**
 * @Date: 2020/8/28 16:01
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class WrapHandlerManager {
    AbstractWrapContext head;

    AbstractWrapContext tail;

    Object context;

    public abstract WrapHandlerManager setContext(Object ctx);

    public abstract WrapHandlerManager addLast(WrapHandler wrapHandler);
}
