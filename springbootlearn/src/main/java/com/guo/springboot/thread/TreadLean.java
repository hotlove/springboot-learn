package com.guo.springboot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/15 21:47
 * @Description:
 */
class PrintScource {

    private int numer = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void print5() throws InterruptedException {
        while (numer != 1) {
            condition.await();
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

        numer = 2;
        condition.signalAll();
    }

    public void print10() throws InterruptedException {
        while (numer != 2) {
            condition.await();
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

        numer = 1;
        condition.signalAll();
    }

}
public class TreadLean {
    public static void main(String[] args) {
        PrintScource printScource = new PrintScource();
        new Thread(() -> {
            try {
                printScource.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                printScource.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
