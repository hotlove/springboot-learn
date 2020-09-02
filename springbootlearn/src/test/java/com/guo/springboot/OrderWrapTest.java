package com.guo.springboot;

import com.guo.springboot.order.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020/9/2 9:11
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class OrderWrapTest {

    @Resource(name = "simpleOrderWrapHandler")
    private SimpleWrapHandler simpleOrderWrapHandler;

    @Resource(name = "simpleOrderWrapHandler2")
    private SimpleWrapHandler simpleOrderWrapHandler2;

    @Test
    public void testOrderWrap() {
        List<String> testList = new ArrayList<>();

        AbstractWrapContext abstractWrapContext = new OrderContext();
        abstractWrapContext.setContent(testList);

        WrapHandlerManager wrapHandlerManager = new WrapHandlerManagerImpl();
        wrapHandlerManager.setContext(abstractWrapContext)
                .addLast(simpleOrderWrapHandler)
                .addLast(simpleOrderWrapHandler2);

        AbstractWrapContext execute = wrapHandlerManager.execute();
        List<String> result = (List<String>) execute.getContent();
        result.forEach(e -> System.out.println(e));
    }
}
