package com.guo.springboot.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperLearn implements Watcher{


    // 同步锁用于客户端连接
    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

//        zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 50000,new ZookeeperLearn());
//        zooKeeper = new ZooKeeper("127.0.0.1:2181", 50000,new ZookeeperLearn());
        zooKeeper = new ZooKeeper("47.99.145.78:2181", 50000,new ZookeeperLearn());

        // 等待zookeeper连接成功
        countDownLatch.await();
        System.out.println("链接成功");

//        zooKeeper.create("/cloud-config", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 第二个参数watch true表示使用默认的监听器
//        byte[] datas = zooKeeper.getData("/cloud-config", true, new Stat());
//        System.out.println(new String(datas));

        new Thread(new ZookeeperMonitor("/cloud-config", zooKeeper)).start();

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("default watcher");
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {

            System.out.println(watchedEvent.getPath());
            System.out.println(watchedEvent.getType());
            System.out.println(Event.EventType.NodeDataChanged == watchedEvent.getType());
            if (Event.EventType.None == watchedEvent.getType() && watchedEvent.getPath() == null) {
                // 同步锁等待连接请求回来
                countDownLatch.countDown();
            } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                System.out.println("节点改变");
                try {
                    System.out.println("配置已修改，新值为" + new String(zooKeeper.getData(watchedEvent.getPath(), true,new Stat())));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
