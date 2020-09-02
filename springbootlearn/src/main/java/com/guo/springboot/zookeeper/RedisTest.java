package com.guo.springboot.zookeeper;

import redis.clients.jedis.Jedis;

import java.io.IOException;

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

//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(50);
//        config.setMaxWaitMillis(3000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
//        // 集群
//        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("47.99.145.78", 7001);
//        JedisShardInfo jedisShardInfo2 = new JedisShardInfo("47.99.145.78", 7002);
//        JedisShardInfo jedisShardInfo3 = new JedisShardInfo("47.99.145.78", 7003);
//        JedisShardInfo jedisShardInfo4 = new JedisShardInfo("47.99.145.78", 7004);
//        JedisShardInfo jedisShardInfo5 = new JedisShardInfo("47.99.145.78", 7005);
//        JedisShardInfo jedisShardInfo6 = new JedisShardInfo("47.99.145.78", 7006);
//        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
//        list.add(jedisShardInfo1);
//        list.add(jedisShardInfo2);
//        list.add(jedisShardInfo3);
//        list.add(jedisShardInfo4);
//        list.add(jedisShardInfo5);
//        list.add(jedisShardInfo6);
//
//        ShardedJedisPool pool = new ShardedJedisPool(config, list);
//
//        ShardedJedis redis = pool.getResource();
//
//        redis.set("test", "key");
//        String value = redis.get("test");
//
//        System.out.println(value);



//        Jedis jedis = new Jedis("47.99.145.78", 7002);
//        String value = jedis.get("test");
//        System.out.println(value);

        //创建集群中相应的节点对象,参数对应节点中的ip和端口号

//        HostAndPort h1  = new HostAndPort("47.99.145.78",7001);
//
//        HostAndPort h2  = new HostAndPort("47.99.145.78",7006);
//
//        HostAndPort h3  = new HostAndPort("47.99.145.78",7002);
//
//        HostAndPort h4  = new HostAndPort("47.99.145.78",7003);
//
//        HostAndPort h5  = new HostAndPort("47.99.145.78",7004);
//
//        HostAndPort h6  = new HostAndPort("47.99.145.78",7005);
//
//
//
//        Set hs= new HashSet();
//
//        //依次放入set集合中
//
//        hs.add(h1);
//
//        hs.add(h2);
//
//        hs.add(h3);
//
//        hs.add(h4);
//
//        hs.add(h5);
//
//        hs.add(h6);
//
//        //将set集合创建集群对象
//
//        JedisCluster jc = new JedisCluster(hs);
//
//        //直接进行相应的操作，和jedis一致
//
//        String value = jc.get("test");
//        System.out.println(value);


        Jedis jedis = new Jedis("47.99.145.78", 6377);
        jedis.auth("accomplish8512");
        jedis.set("foo", "bar");

        String value = jedis.get("foo");
        System.out.println(value);
    }



}
