package com.guo.springboot.redis;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/12/5 19:18
 * @Description:
 */
public class RedisUtil {

    private RedisUtil() {}

    private static class Holder {
        private static RedisUtil instance = new RedisUtil();
    }

    public static RedisUtil getInstance() {
        return Holder.instance;
    }

}
