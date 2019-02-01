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

    private ZooKeeper getZookeeperClient() {

        if (zooKeeper == null) {
            synchronized (this) {
                if (zooKeeper == null) {

                    try {
//                    zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
//                            5000, new ZookeeperFactory());
                        zooKeeper = new ZooKeeper("47.99.145.78:2181,47.99.145.78:2182,47.99.145.78:2183",
                                5000, new ZookeeperFactory());

                        connectSemphore.await();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("监听到了-----------------");
            connectSemphore.countDown();
        }
    }

    public String createSync(String path, String content, CreateMode createMode) {
        try {
            if (this.getZookeeperClient() != null) {
                return this.getZookeeperClient().create(path, content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
