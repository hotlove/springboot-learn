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

        WrapHandlerManager wrapHandlerManager = new OrderWrapHandlerManager();
        wrapHandlerManager.setContext(abstractWrapContext)
                .addLast(new WrapHandler() {
                    @Override
                    public void handler(AbstractWrapContext ctx) {
                        if (ctx instanceof OrderContext) {

                        }
                        List<String> list = (List<String>) ctx.getContent();
                        list.add("测试1");
                    }
                }).addLast(new WrapHandler() {
            @Override
            public void handler(AbstractWrapContext ctx) {
                List<String> list = (List<String>) ctx.getContent();
                list.add("测试2");
            }
        });

        AbstractWrapContext execute = wrapHandlerManager.execute();
        List<String> result = (List<String>) execute.getContent();
        result.forEach(e -> System.out.println(e));
    }
}
