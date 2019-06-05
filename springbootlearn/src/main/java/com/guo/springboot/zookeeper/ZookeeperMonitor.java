package com.guo.springboot.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperMonitor implements Runnable, Watcher {

    private String path;

    private ZooKeeper zooKeeper;

    public ZookeeperMonitor(String path, ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            zooKeeper.getData(path, this, new Stat());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                try {
                    System.out.println("更新后得值:" + new String(zooKeeper.getData(watchedEvent.getPath(), this, new Stat())));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
