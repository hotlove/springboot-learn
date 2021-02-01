package com.guo.springboot;

import java.util.Calendar;
import java.util.Date;

/**
 * @Date: 2020/12/18 11:25
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class JVMParmTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        testBigObject();
//        for (; ; ) {
//            getGarlic();
//        }
        Float fa = new Float(100);
        int num = (int) Math.ceil(50 * 3 / fa);
        float a = 50*3/fa;
        System.out.println(a);
        System.out.println(num);
    }

    public static void getGarlic() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

    public static void testBigObject() {
        byte[] allocation1 = new byte[4 * _1MB];
    }
}
