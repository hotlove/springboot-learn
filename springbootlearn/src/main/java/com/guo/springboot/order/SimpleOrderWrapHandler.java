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
@Service("simpleOrderWrapHandler")
public class SimpleOrderWrapHandler extends SimpleWrapHandler<OrderContext>{
    @Override
    public void handler0(OrderContext ctx) {
        try {
            List<OrderMain> list = (List<OrderMain>) ctx.getContent();
            for (OrderMain orderMain : list) {
                orderMain.setOrderNo("1234");
            }
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试1");
    }
}
