package com.guo.springboot.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.apache.log4j.Logger;

import java.util.*;

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

        try {

            RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

            commands.set(key, value);

        } catch (Exception ex) {
            logger.info(ex);
        }

    }

    public String get(String key) {
        try {
            RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

            return commands.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long incrementAndGet (String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        return commands.incr(key);
    }

    public Long incrementAndGet(String key, Long size) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        return commands.incrby(key, size);
    }

    /**
     * 存储map结构数据
     * @param key
     * @param mapValue
     * @return
     */
    public String setMap(String key, Map<String, String> mapValue) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();
        try {
            return commands.hmset(key, mapValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取map值
     * @param key
     * @return
     */
    public Map<String, String> getMap(String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            return commands.hgetall(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * lpush 列表
     * @param key
     * @param values
     * @return
     */
    public Long push(String key, List<String> values) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            String []temp = new String[values.size()];
            return commands.lpush(key, values.toArray(temp));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
        }
        return null;
    }

    /**
     * lpush 单个值
     * @param key
     * @param value
     * @return
     */
    public Long push(String key, String value) {

        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            return commands.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
        }

        return 0L;
    }

    /**
     * lpop 弹出队列中的第一个元素
     * @param key
     * @return
     */
    public String pop(String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            return commands.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
        }
        return null;
    }

    /**
     * set 无序集合  里面存放的值是唯一的
     * @param key
     * @param value
     * @return
     */
    public Long addSet(String key, String value) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            return commands.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 增加set集合批量
     * @param key
     * @param values
     * @return
     */
    public Long addSet(String key, Set<String> values) {

        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        String []temp = new String[values.size()];
        try {
            commands.sadd(key, values.toArray(temp));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
        }
        return 0L;
    }

    public Set<String> getSet(String key) {
        RedisAdvancedClusterCommands<String, String> commands = this.getCommands();

        try {
            String randomStr = commands.spop(key);
            Set<String> result = new HashSet<>();
            result.add(randomStr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
