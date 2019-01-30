package com.guo.springboot.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperTest implements Watcher {


    private static CountDownLatch connectSemphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException {

        zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000,new ZookeeperTest());

        try {
            connectSemphore.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println("Receive watched event");

        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectSemphore.countDown();
        }

    }

    // 同步创建节点
    public static String createNodeSync(String path, String content, CreateMode createMode) {
        try {
            String nodeName = zooKeeper.create(path, content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            return nodeName;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 异步创建节点
    public static void createNodeAsync(String path, String content, CreateMode createMode, AsyncCallback.StringCallback stringCallback, Object context) {
        zooKeeper.create(path, content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode, stringCallback, context);
    }
}
