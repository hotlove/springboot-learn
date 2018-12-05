package com.guo.springboot.redis;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/11/29 21:17
 * @Description:
 */
public class RedisTest {

    public static void main(String[] args) {

//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        // 最大连接数
//        poolConfig.setMaxTotal(1);
//        // 最大空闲数
//        poolConfig.setMaxIdle(1);
//        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
//        // Could not get a resource from the pool
//        poolConfig.setMaxWaitMillis(1000);
//        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//        nodes.add(new HostAndPort("47.99.145.78", 7001));
//        nodes.add(new HostAndPort("47.99.145.78", 7002));
//        nodes.add(new HostAndPort("47.99.145.78", 7003));
//        nodes.add(new HostAndPort("47.99.145.78", 7004));
//        nodes.add(new HostAndPort("47.99.145.78", 7005));
//        nodes.add(new HostAndPort("47.99.145.78", 7006));
//        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
//
//        String name = cluster.get("test");
//        System.out.println("name------------" + name);

//        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//        nodes.add(new HostAndPort("47.99.145.78", 7001));
//        nodes.add(new HostAndPort("47.99.145.78", 7002));
//        nodes.add(new HostAndPort("47.99.145.78", 7003));
//        nodes.add(new HostAndPort("47.99.145.78", 7004));
//        nodes.add(new HostAndPort("47.99.145.78", 7005));
//        nodes.add(new HostAndPort("47.99.145.78", 7006));
//        //Jedis Cluster will attempt to discover cluster nodes automatically
////        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
//        JedisCluster jc = new JedisCluster(nodes);
//        jc.set("foo1", "bar");
//        String value = jc.get("foo1");
//
//        System.out.println(value);

        List<RedisURI> list = new ArrayList<>();
        list.add(RedisURI.create("redis://47.99.145.78:7001"));
        list.add(RedisURI.create("redis://47.99.145.78:7002"));
        list.add(RedisURI.create("redis://47.99.145.78:7003"));
        list.add(RedisURI.create("redis://47.99.145.78:7004"));
        list.add(RedisURI.create("redis://47.99.145.78:7005"));
        list.add(RedisURI.create("redis://47.99.145.78:7006"));
        RedisClusterClient clusterClient = RedisClusterClient.create(list);
        //集群Redis
        RedisClusterClient client = RedisClusterClient.create(list);
        GenericObjectPool<StatefulRedisClusterConnection<String, String>> pool;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxTotal(16);
        poolConfig.setMinEvictableIdleTimeMillis(1000*30);
        poolConfig.setSoftMinEvictableIdleTimeMillis(1000*30);
        poolConfig.setMaxWaitMillis(0);
        pool = ConnectionPoolSupport.createGenericObjectPool(() -> {
            System.err.println("Requesting new StatefulRedisClusterConnection "+System.currentTimeMillis());
            return client.connect();
        }, poolConfig);

        StatefulRedisClusterConnection<String, String> connection = null;
        try {
            connection = pool.borrowObject();
            connection.setReadFrom(ReadFrom.MASTER_PREFERRED);

            RedisAdvancedClusterAsyncCommands<String, String> commands = connection.async();
            commands.set("id","taozhongyu");
            RedisFuture<String> future = commands.get("id");
            String str = future.get();
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.close();
        clusterClient.shutdown();

    }

}
