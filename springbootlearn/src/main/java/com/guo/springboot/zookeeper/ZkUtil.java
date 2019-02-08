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

    public String creteNodeEphemeral(String path, String content) {

        /**
         * CreateMode
         * PERSISTENT  持久节点,
         * PERSISTENT_SEQUENTIAL 持节顺序节点,
         * EPHEMERAL  临时节点,
         * EPHEMERAL_SEQUENTIAL 临时顺序节点;
         */
        return this.createNode(path, content, CreateMode.EPHEMERAL);
    }

    /**
     * 创建临时节点 带顺序
     * @param path
     * @param content
     * @return
     */
    public String createNodeEphemeralSeq(String path, String content) {
        return this.createNode(path, content, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    /**
     * 创建节点
     * @param path
     * @param content
     * @param nodeMode 节点模式
     * @return
     */
    public String createNode(String path, String content, CreateMode nodeMode) {
       return ZookeeperFactory.getInstace().createSync(path, content, nodeMode);
    }

    /**
     * 同步删除节点信息
     * @param path
     * @param version
     */
    public void deleteSync(String path, int version) {
        ZookeeperFactory.getInstace().delete(path, version);
    }
}
