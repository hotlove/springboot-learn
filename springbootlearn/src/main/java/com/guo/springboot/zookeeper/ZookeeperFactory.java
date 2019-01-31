package com.guo.springboot.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperFactory implements Watcher{

    private static CountDownLatch connectSemphore = new CountDownLatch(1);

    private volatile ZooKeeper zooKeeper = null;

    private static class Holder {
        private static ZookeeperFactory INSTANCE = new ZookeeperFactory();
    }

    public static ZookeeperFactory getInstace() {
        return Holder.INSTANCE;
    }

    public void init() throws IOException {

        if (zooKeeper == null) {
            synchronized (this) {
                if (zooKeeper == null) {
//                    zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
//                            5000, new ZookeeperFactory());
                    zooKeeper = new ZooKeeper("47.99.145.78:2181,47.99.145.78:2182,47.99.145.78:2183",
                            5000, new ZookeeperFactory());
                    try {
                        connectSemphore.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectSemphore.countDown();
        }
    }

    public String createSync(String path, String content, CreateMode createMode) {
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
}
