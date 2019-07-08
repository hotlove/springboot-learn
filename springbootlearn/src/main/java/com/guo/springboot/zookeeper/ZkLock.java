package com.guo.springboot.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkLock implements Watcher {

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zooKeeper = null;

    private volatile boolean flag = false;

    public ZooKeeper newZkLock() throws IOException, InterruptedException {

        zooKeeper = new ZooKeeper("47.99.145.78:2181", 50000,new ZookeeperLearn());

        countDownLatch.await();

        return zooKeeper;
    }

    public boolean tryAccquire(String path) throws IOException, InterruptedException, KeeperException {

        this.flag = this.createNode(path);

        if (this.flag) {
            return this.flag;
        } else {

            // 没有获取到锁 等待别人释放锁
            while (!this.flag) {

            }
            return true;

        }
    }

    private boolean createNode(String path) throws IOException, InterruptedException, KeeperException {
        if (zooKeeper == null) {
            zooKeeper = this.newZkLock();
        }

        String responsePath = zooKeeper.create(path, "exclusive_lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE ,CreateMode.EPHEMERAL);
        if (StringUtils.isNotBlank(responsePath)) {
            return true;
        }
        return false;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("监听到了-----------------");
            countDownLatch.countDown();
        }
//        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
//
//            System.out.println(watchedEvent.getPath());
//            System.out.println(watchedEvent.getType());
//            System.out.println(Event.EventType.NodeDataChanged == watchedEvent.getType());
//            if (Event.EventType.None == watchedEvent.getType() && watchedEvent.getPath() == null) {
//                // 同步锁等待连接请求回来
//                countDownLatch.countDown();
//            }
//            else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
//                System.out.println("节点改变");
//                try {
//                    System.out.println("配置已修改，新值为" + new String(zooKeeper.getData(watchedEvent.getPath(), true,new Stat())));
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
