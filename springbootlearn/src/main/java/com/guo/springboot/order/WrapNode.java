package com.guo.springboot.order;

/**
 * @Date: 2020/9/1 13:52
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class WrapNode implements WrapHandler{
    protected WrapNode prev;

    protected WrapNode next;

    private WrapHandler wrapHandler;

    @Override
    public void handler(AbstractWrapContext ctx) {}


    public WrapHandler getWrapHandler() {
        return wrapHandler;
    }

    public void setWrapHandler(WrapHandler wrapHandler) {
        this.wrapHandler = wrapHandler;
    }
}
