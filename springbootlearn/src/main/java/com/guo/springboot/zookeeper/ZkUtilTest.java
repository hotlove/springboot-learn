package com.guo.springboot.zookeeper;

import java.util.concurrent.CountDownLatch;

public class ZkUtilTest {

    public static void main(String[] args) throws InterruptedException {

        // String result = ZkUtil.getInstance().creteNodePersistentSeq("/zookeeper/lock1", "测试工具累");

        String result = ZkUtil.getInstance().createNodeEphemeralSeq("/zookeeper/mutex", "not share lock");
        System.out.println(result == null ? "失败了" : result);
        Thread.sleep(1000000);

    }
}
