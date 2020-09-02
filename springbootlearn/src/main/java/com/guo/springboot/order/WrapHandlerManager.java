package com.guo.springboot.order;

public interface WrapHandlerManager {

    WrapHandlerManager setContext(AbstractWrapContext ctx);

    WrapHandlerManager addLast(WrapHandler wrapHandler);

    WrapHandlerManager addParallelLast(WrapHandler wrapHandler);

    AbstractWrapContext execute();
}
