package com.guo.springboot.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2020/9/2 9:10
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@Service("simpleOrderWrapHandler2")
public class SimpleOrderWrapHandler2 extends SimpleWrapHandler<OrderContext> {
    @Override
    public void handler0(OrderContext ctx) {
        List<OrderMain> list = (List<OrderMain>) ctx.getContent();
        for (OrderMain orderMain : list) {
            orderMain.setCreatedByName("小强");
        }
        System.out.println("测试2");
    }
}
