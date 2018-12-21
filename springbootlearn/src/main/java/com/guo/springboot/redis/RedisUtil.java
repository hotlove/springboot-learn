package com.guo.springboot.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2018/12/5 19:18
 * @Description:
 */
public class RedisUtil {

    Logger logger = Logger.getLogger(this.getClass());

    private final String HOST = "47.99.145.78:7001,47.99.145.78:7002,47.99.145.78:7003,47.99.145.78:7004,47.99.145.78:7005,47.99.145.78:7006";

    private volatile StatefulRedisClusterConnection<String, String> connection = null;

    private RedisUtil() {}

    private static class Holder {
        private static RedisUtil instance = new RedisUtil();
    }

    public static RedisUtil getInstance() {
        return Holder.instance;
    }

    private List<RedisURI> getRedisNodes() {
        String []hosts = HOST.split(",");

        List<RedisURI> nodes = new ArrayList<>(hosts.length);

        for (String host : hosts) {
            nodes.add(RedisURI.create("redis://"+host));
        }

        return nodes;
    }

    private RedisAdvancedClusterCommands<String, String> getCommands() {

        // dcl 保证获取到的connection 是唯一的
        if (connection == null) {
            synchronized (this) {
                if (connection == null) {
                    List<RedisURI> nodes = this.getRedisNodes();
                    RedisClusterClient client = RedisClusterClient.create(nodes);
                    connection = client.connect();
                }
            }
        }
        return connection.sync();
    }

    public void set(String key, String value) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        commands.set(key, value);
    }

    public String get(String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        return commands.get(key);
    }

    public Long incrementAndGet (String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        return commands.incr(key);
    }

    public Long incrementAndGet(String key, Long size) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        return commands.incrby(key, size);
    }
}
