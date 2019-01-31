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

            /**
             * CreateMode
             * PERSISTENT  持久节点,
             * PERSISTENT_SEQUENTIAL 持节顺序节点,
             * EPHEMERAL  临时节点,
             * EPHEMERAL_SEQUENTIAL 临时顺序节点;
             */
            return zookeeperFactory.createSync(path, content, CreateMode.PERSISTENT);

        }
        return null;
    }

    public String creteNodeEPHEMERAL(String path, String content) {

        if (zookeeperFactory != null) {

            /**
             * CreateMode
             * PERSISTENT  持久节点,
             * PERSISTENT_SEQUENTIAL 持节顺序节点,
             * EPHEMERAL  临时节点,
             * EPHEMERAL_SEQUENTIAL 临时顺序节点;
             */
            return zookeeperFactory.createSync(path, content, CreateMode.EPHEMERAL);

        }
        return null;
    }
}
