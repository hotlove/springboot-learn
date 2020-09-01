package com.guo.springboot.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020/9/1 15:56
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@Component("AppContext")
public class AppContext implements ApplicationContextAware {
    private static ApplicationContext context;
    private static Logger log = LoggerFactory.getLogger(AppContext.class);

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
        log.info("ApplicationContext injection completed.");
    }

    public static ApplicationContext getAppContext() {
        return context;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
