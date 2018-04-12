package com.guo.springboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. @Component 让spring 管理生命周期
 * 2. 增加配置文件注册拦截器
 */
@Component
public class TiemInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, java.lang.Object handler) throws Exception {
        System.out.println("========preHandle=========");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());

        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, java.lang.Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("========postHandle=========");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, java.lang.Object o, Exception e) throws Exception {
        System.out.println("========afterCompletion=========");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));

        System.out.println(e);
    }
}
