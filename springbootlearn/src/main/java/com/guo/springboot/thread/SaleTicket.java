package com.guo.springboot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date: 2021/4/14 9:02
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
class Ticket {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {

            if (number != 0) {
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void deincreament() {
        lock.lock();
        try {
            if (number != 1) {
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.increment();
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.deincreament();
            }

        }, "B").start();
    }
}
