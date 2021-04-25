package com.guo.springboot.design;

import java.lang.reflect.Proxy;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/25 22:58
 * @Description:
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("method:"+method.getName());
                    return method.invoke(target, args);
                });
    }

}
