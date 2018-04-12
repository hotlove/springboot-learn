package com.guo.springboot.aop;

import org.springframework.stereotype.Component;

//@Aspect
@Component
public class TimeAspect {

//    @Around("execution(* com.guo.springboot.service.*..*(..))")
//    public Object method(ProceedingJoinPoint pjp) throws Throwable {
//
//        System.out.println("=====Aspect处理=======");
//        Object[] args = pjp.getArgs();
//        for (Object arg : args) {
//            System.out.println("参数为:" + arg);
//        }
//
//        long start = System.currentTimeMillis();
//
//        Object object = pjp.proceed();
//
//        System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));
//
//        return object;
//    }

}
