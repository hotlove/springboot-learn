package com.guo.springboot.zookeeper;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

import java.util.Arrays;

public class RedisTest {


    public static void main(String[] args) {

        RedisURI node1 = RedisURI.create("47.99.145.78", 7001);
        RedisURI node2 = RedisURI.create("47.99.145.78", 7002);
        RedisURI node3 = RedisURI.create("47.99.145.78", 7003);
        RedisURI node4 = RedisURI.create("47.99.145.78", 7004);
        RedisURI node5 = RedisURI.create("47.99.145.78", 7005);
        RedisURI node6 = RedisURI.create("47.99.145.78", 7006);

        RedisClusterClient redisClusterClient =
                RedisClusterClient.create(Arrays.asList(node1, node2, node3, node4, node5, node6));

        StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();

        RedisAdvancedClusterCommands<String, String> commands = connection.sync();

        commands.set("test", "testfoo");

        String value = commands.get("test");

        System.out.println(value);

//        connection.close();
//        redisClusterClient.shutdown();

//        RedisClient client = RedisClient.create("redis://47.99.145.78:7001");
//        StatefulRedisConnection<String, String> connection = client.connect();
//        RedisStringCommands sync = connection.sync();
//        String value = (String) sync.get("key");

    }

}
