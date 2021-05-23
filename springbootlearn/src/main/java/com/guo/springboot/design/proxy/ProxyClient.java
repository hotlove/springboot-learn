package com.guo.springboot.design.proxy;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/25 23:04
 * @Description:
 */
public class ProxyClient {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new ProfileServiceImpl());
        ProfileService proxyInstance = (ProfileService) proxyFactory.getProxyInstance();
        System.out.println(proxyInstance.getProfileName("测试得"));
        System.out.println(proxyInstance.getProfileSex("测试i性别"));
    }
}
