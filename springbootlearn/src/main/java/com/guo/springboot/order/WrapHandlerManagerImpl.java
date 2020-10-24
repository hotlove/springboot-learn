package com.guo.springboot.order;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date: 2020/8/28 16:48
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */

@Service
public class WrapHandlerManagerImpl implements WrapHandlerManager {

    protected WrapNode head;

    protected WrapNode tail;

    public AbstractWrapContext context;

    private List<WrapNode> parallelHandles = new ArrayList<>();

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
        wrapNode.setHandlerConfig(new HandlerConfig(HandlerConfig.EXECUTE_SERIAL));

        // 构建指针链表
        buildNode(wrapNode);

        return this;
    }

    @Override
    public WrapHandlerManager addParallelLast(WrapHandler wrapHandler) {
        // 构建上下文对象
        WrapNode wrapNode = new WrapNode();
        wrapNode.setWrapHandler(wrapHandler);
        wrapNode.setHandlerConfig(new HandlerConfig(HandlerConfig.EXECUTE_PARALLEL));

        // 构建指针链表
        parallelHandles.add(wrapNode);

        return this;
    }

    @Override
    public AbstractWrapContext execute() {
        if (parallelHandles != null && parallelHandles.size() > 0) {
            new Thread(() -> {
                executeParallelHanlders();
            }).start();
        }
        WrapNode executeNode = this.head.next;
        while (executeNode.next != null) {
            executeNode.getWrapHandler().handler(this.context);
            executeNode = executeNode.next;
        }

        return this.context;
    }

    // 执行并行处理器
    private void executeParallelHanlders() {
        for (WrapNode parallelHandle : parallelHandles) {
            new Thread(() -> {
                parallelHandle.getWrapHandler().handler(this.context);
            }).start();
        }
    }

    // 构建处理器节点
    private void buildNode(WrapNode wrapNode) {
        // 构建指针链表
        this.tail.prev.next = wrapNode;
        wrapNode.prev = this.tail.prev;

        wrapNode.next = this.tail;
        this.tail.prev = wrapNode;
    }
}
