package com.guo.springboot.controller;

import com.guo.springboot.order.*;
import com.guo.springboot.service.OrderBaseInfoWraperHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/9/1 15:26
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@RestController
public class OrderController {

    @GetMapping("/getorder")
    public List<OrderMain> getWrapOrder() {

        List<OrderMain> list = new ArrayList<>();
        OrderMain orderMain = new OrderMain();
        orderMain.setCreatedById(1);
        orderMain.setOrderNo("2313");
        list.add(orderMain);

        AbstractWrapContext abstractWrapContext = new OrderContext();
        abstractWrapContext.setContent(list);

        WrapHandlerManager wrapHandlerManager = new OrderWrapHandlerManager();
        AbstractWrapContext context = wrapHandlerManager.setContext(abstractWrapContext)
                .addLast(new OrderBaseInfoWraperHandler())
                .execute();

        List<OrderMain> result = (List<OrderMain>) context.getContent();
        return result;
    }
}
