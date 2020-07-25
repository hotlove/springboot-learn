package com.guo.springboot.order;

/**
 * @Date: 2020/7/24 17:54
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class AbstractContext {
    protected AbstractContext next;
    protected AbstractContext prev;

    protected ContextHandler contextHandler;
}
