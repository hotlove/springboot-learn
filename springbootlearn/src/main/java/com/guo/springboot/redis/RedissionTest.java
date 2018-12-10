package com.guo.springboot.redis;

import org.redisson.Redisson;
import org.redisson.api.RExecutorService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/12/5 20:09
 * @Description:
 */
public class RedissionTest {

    public static void main(String[] args) {

        Config config = new Config();

        config.useClusterServers().setScanInterval(2000)
                .addNodeAddress(
                        "redis://47.99.145.78:7001",
                        "redis://47.99.145.78:7002",
                        "redis://47.99.145.78:7003",
                        "redis://47.99.145.78:7004",
                        "redis://47.99.145.78:7005",
                        "redis://47.99.145.78:7006");

        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("myLock");

    }
}
