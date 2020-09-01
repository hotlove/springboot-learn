package com.guo.springboot.order;

public interface WrapHandlerManager {

    WrapHandlerManager setContext(AbstractWrapContext ctx);

    WrapHandlerManager addLast(WrapHandler wrapHandler);

    AbstractWrapContext execute();
}
