package com.guo.springboot.order;

import org.springframework.stereotype.Service;

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
        System.out.println("测试2");
    }
}
