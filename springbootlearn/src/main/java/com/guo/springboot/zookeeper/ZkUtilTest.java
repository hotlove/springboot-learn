package com.guo.springboot.zookeeper;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class ZkUtilTest {

    public static void main(String[] args) throws InterruptedException {

        // String result = ZkUtil.getInstance().creteNodePersistentSeq("/zookeeper/lock1", "测试工具累");

//        String result = ZkUtil.getInstance().createNodeEphemeralSeq("/zookeeper/mutex", "not share lock");
//        System.out.println(result == null ? "失败了" : result);
//        Thread.sleep(1000000);

//        BigDecimal b1 = new BigDecimal(11);
//
//        Integer a = 11;
//        BigDecimal b2 = new BigDecimal(a.intValue());
//
//
//
//        System.out.println(b1.compareTo(b2));
//
//        System.out.println(b1.add(b2));

//        List<User> testlist = new ArrayList<>(4);
//
//        for (int i =0; i < 4; i++) {
//            User user = new User();
//            user.setAge(i);
//            user.setHeight(new BigDecimal(i + 1));
//            testlist.add(user);
//        }
//
//        BigDecimal total = testlist.stream().map(User::getHeight).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println(total);

        List<Integer> dateList = new ArrayList<>();
        dateList.add(2);
        dateList.add(1);

        List<Integer> sortDate = dateList.stream().sorted((a, b) -> {
            if (a.intValue() > b.byteValue()) {
                return 1;
            } else {
                return -1;
            }
        }).collect(Collectors.toList());

        System.out.println(JSON.toJSON(sortDate));


    }

    static class User {
        int age;
        BigDecimal height;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public BigDecimal getHeight() {
            return height;
        }

        public void setHeight(BigDecimal height) {
            this.height = height;
        }
    }
}
