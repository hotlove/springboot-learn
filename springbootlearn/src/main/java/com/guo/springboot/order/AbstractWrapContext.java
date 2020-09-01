package com.guo.springboot.order;

/**
 * @Date: 2020/8/28 15:58
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class AbstractWrapContext {
    public Object content;

    public abstract Object getContent();

    public abstract void setContent(Object content);
}
