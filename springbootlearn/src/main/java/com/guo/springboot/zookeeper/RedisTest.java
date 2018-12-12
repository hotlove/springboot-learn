package com.guo.springboot.zookeeper;

import com.guo.springboot.orderenum.OrderEnum;
import com.guo.springboot.redis.RedisUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class RedisTest {


    public static void main(String[] args) throws IOException {

//        RedisURI node1 = RedisURI.create("47.99.145.78", 7001);
//        RedisURI node2 = RedisURI.create("47.99.145.78", 7002);
//        RedisURI node3 = RedisURI.create("47.99.145.78", 7003);
//        RedisURI node4 = RedisURI.create("47.99.145.78", 7004);
//        RedisURI node5 = RedisURI.create("47.99.145.78", 7005);
//        RedisURI node6 = RedisURI.create("47.99.145.78", 7006);
//
//        RedisClusterClient redisClusterClient =
//                RedisClusterClient.create(Arrays.asList(node1, node2, node3, node4, node5, node6));
//
//        StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
//
//        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
//
//        commands.set("test", "testfoo111");
//
//        String value = commands.get("test");
//
//        System.out.println(value);

//        connection.close();
//        redisClusterClient.shutdown();

//        RedisClient client = RedisClient.create("redis://47.99.145.78:7001");
//        StatefulRedisConnection<String, String> connection = client.connect();
//        RedisStringCommands sync = connection.sync();
//        String value = (String) sync.get("key");

//        for (int i = 0; i < 10 ; i++) {
//            int finalI = i;
//            new Thread(() -> {
//
//                RedisUtil.getInstance().set("redis"+ finalI, "cluster test"+ finalI);
//
//            }).start();
//        }
//        RedisUtil.getInstance().set("redisutil", "redisutils");
//
//        String value = RedisUtil.getInstance().get("redisutil");
//
//        System.out.println(value);

//        System.out.println(RedisUtil.getInstance().incrementAndGet("orderId"));

        HashSet<HostAndPort> node = new HashSet();
        node.add(new HostAndPort("47.99.145.78",7001));
        node.add(new HostAndPort("47.99.145.78",7002));
        node.add(new HostAndPort("47.99.145.78",7003));
        node.add(new HostAndPort("47.99.145.78",7004));
        node.add(new HostAndPort("47.99.145.78",7005));
        node.add(new HostAndPort("47.99.145.78",7006));

        JedisCluster cluster = new JedisCluster(node);
        cluster.set("orderkey", "orderdfasdfa");
        System.out.println(cluster.get("orderkey"));
        cluster.close();

    }

}
