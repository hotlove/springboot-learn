package com.guo.springboot.zookeeper;

import org.apache.zookeeper.CreateMode;

import java.io.IOException;


public class ZkUtil {

    private static ZookeeperFactory zookeeperFactory = null;

    static {
        zookeeperFactory = ZookeeperFactory.getInstace();
        try {
            zookeeperFactory.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static ZkUtil zkUtil = new ZkUtil();
    }

    public static ZkUtil getInstance() {
        return Holder.zkUtil;
    }

    public String creteNode(String path, String content) {

        if (zookeeperFactory != null) {

            return zookeeperFactory.createSync(path, content, CreateMode.EPHEMERAL);

        }
        return null;
    }
}
