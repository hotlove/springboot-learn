package com.guo.springboot.zookeeper;

public class ZkUtilTest {

    public static void main(String[] args) {

        String result = ZkUtil.getInstance().creteNodePersistentSeq("/zookeeper/lock1", "测试工具累");

        System.out.println(result == null ? "失败了" : result);
    }
}
