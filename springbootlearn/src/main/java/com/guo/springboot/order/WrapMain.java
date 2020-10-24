package com.guo.springboot.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/9/1 14:09
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class WrapMain {
    public static void main(String[] args) {
        List<String> testList = new ArrayList<>();

        AbstractWrapContext abstractWrapContext = new OrderContext();
        abstractWrapContext.setContent(testList);

        WrapHandlerManager wrapHandlerManager = new WrapHandlerManagerImpl();
        wrapHandlerManager.setContext(abstractWrapContext)
                .addLast(new SimpleWrapHandler<OrderContext>() {
                    @Override
                    public void handler0(OrderContext ctx) {
                        System.out.println("测试1");
                    }
                })
                .addLast(new SimpleWrapHandler<OrderContext>() {
                    @Override
                    public void handler0(OrderContext ctx) {
                        System.out.println("测试2");
                    }
                });

        AbstractWrapContext execute = wrapHandlerManager.execute();
        List<String> result = (List<String>) execute.getContent();
        result.forEach(e -> System.out.println(e));
    }
}
