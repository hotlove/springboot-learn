package com.guo.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("初始化监听器......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
