package com.guo.springboot.springlean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/5/5 11:10
 * @Description:
 */
@Component
public class BeanProcessror implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(bean+"----"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after-"+bean+"---"+beanName);
        return bean;
    }
}
