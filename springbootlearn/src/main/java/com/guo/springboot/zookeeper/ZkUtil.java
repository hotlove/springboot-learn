package com.guo.springboot.zookeeper;

import org.apache.zookeeper.CreateMode;

import java.io.IOException;


public class ZkUtil {

    private static class Holder {
        private static ZkUtil zkUtil = new ZkUtil();
    }

    public static ZkUtil getInstance() {
        return Holder.zkUtil;
    }

    public String creteNodePersistent(String path, String content) {

        /**
         * CreateMode
         * PERSISTENT  持久节点,
         * PERSISTENT_SEQUENTIAL 持节顺序节点,
         * EPHEMERAL  临时节点,
         * EPHEMERAL_SEQUENTIAL 临时顺序节点;
         */
        return this.createNode(path, content, CreateMode.PERSISTENT);
    }

    public String creteNodePersistentSeq(String path, String content) {

        /**
         * CreateMode
         * PERSISTENT  持久节点,
         * PERSISTENT_SEQUENTIAL 持节顺序节点,
         * EPHEMERAL  临时节点,
         * EPHEMERAL_SEQUENTIAL 临时顺序节点;
         */
        return this.createNode(path, content, CreateMode.PERSISTENT_SEQUENTIAL);

    }

    public String creteNodeEPHEMERAL(String path, String content) {

        /**
         * CreateMode
         * PERSISTENT  持久节点,
         * PERSISTENT_SEQUENTIAL 持节顺序节点,
         * EPHEMERAL  临时节点,
         * EPHEMERAL_SEQUENTIAL 临时顺序节点;
         */
        return this.createNode(path, content, CreateMode.EPHEMERAL);
    }

    public String createNode(String path, String content, CreateMode nodeMode) {
       return ZookeeperFactory.getInstace().createSync(path, content, nodeMode);
    }
}
