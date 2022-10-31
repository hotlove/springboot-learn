package com.guo.springboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/7/20 23:19
 * @Description:
 */
@Component
public class MyApplicationContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(TestService.class);
        System.out.println("=====================================");
        System.out.println(beansWithAnnotation.toString());
        System.out.println("=======================================");
    }
}
