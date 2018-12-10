package com.guo.springboot.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class ZkClientTest {

    public static void main(String[] args) throws InterruptedException {

        String path = "/zk-book";

        ZkClient zkClient = new ZkClient("47.99.145.78:2181,47.99.145.78:2182,47.99.145.78:2183", 5000);

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + " s child changed, currentChilds ");
            }
        });

        zkClient.createPersistent(path);
        Thread.sleep(1000);

        System.out.println(zkClient.getChildren(path));

        Thread.sleep(1000);
        zkClient.createPersistent(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path + "/c1");
        Thread.sleep(1000);
        zkClient.delete(path);

        Thread.sleep(Integer.MAX_VALUE);

    }
}
