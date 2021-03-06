package com.guo.springboot.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class ZkClientTest {

    public static void main(String[] args) throws InterruptedException {

//        String path = "/zk-book";
//
//        ZkClient zkClient = new ZkClient("47.99.145.78:2181,47.99.145.78:2182,47.99.145.78:2183", 5000);
//
//        zkClient.subscribeChildChanges(path, new IZkChildListener() {
//            @Override
//            public void handleChildChange(String s, List<String> list) throws Exception {
//                System.out.println(s + " s child changed, currentChilds ");
//            }
//        });
//
//        zkClient.createPersistent(path);
//        Thread.sleep(1000);
//
//        System.out.println(zkClient.getChildren(path));
//
//        Thread.sleep(1000);
//        zkClient.createPersistent(path + "/c1");
//        Thread.sleep(1000);
//        zkClient.delete(path + "/c1");
//        Thread.sleep(1000);
//        zkClient.delete(path);

        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        zkClient.subscribeDataChanges("/cloud-config", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(s);
                System.out.println(o.toString());
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        Thread.sleep(Integer.MAX_VALUE);

    }
}
