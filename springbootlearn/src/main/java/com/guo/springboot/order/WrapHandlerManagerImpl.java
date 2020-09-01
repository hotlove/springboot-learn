package com.guo.springboot.order;

/**
 * @Date: 2020/8/28 16:48
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class WrapHandlerManagerImpl implements WrapHandlerManager {

    protected WrapNode head;

    protected WrapNode tail;

    public AbstractWrapContext context;

    public WrapHandlerManagerImpl() {
        this.head = new WrapNode();
        this.tail = new WrapNode();

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    @Override
    public WrapHandlerManager setContext(AbstractWrapContext ctx) {
        this.context = ctx;
        return this;
    }

    @Override
    public WrapHandlerManager addLast(WrapHandler wrapHandler) {

        // 构建上下文对象
        WrapNode wrapNode = new WrapNode();
        wrapNode.setWrapHandler(wrapHandler);

        // 构建指针链表
        this.tail.prev.next = wrapNode;
        wrapNode.prev = this.tail.prev;

        wrapNode.next = this.tail;
        this.tail.prev = wrapNode;

        return this;
    }

    @Override
    public AbstractWrapContext execute() {

        WrapNode executeNode = this.head.next;
        while (executeNode.next != null) {
            executeNode.getWrapHandler().handler(this.context);

            executeNode = executeNode.next;
        }

        return this.context;
    }
}
