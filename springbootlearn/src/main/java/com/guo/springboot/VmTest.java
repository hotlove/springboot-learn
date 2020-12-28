package com.guo.springboot;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CheckedOutputStream;

public class VmTest {
    public volatile int i = 0;

    public void addI() {
        i++;
        System.out.println("i="+i);
    }

    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition = reentrantLock.newCondition();
    public static void main(String[] args) throws InterruptedException {
//        reentrantLock.lock();
//        reentrantLock.unlock();
//        DeadLock deadLock = new DeadLock();
//        new Thread(() -> {
//            try {
//                deadLock.method1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                deadLock.method2();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        new Thread(() -> {

            try {
                reentrantLock.lock();
                System.out.println("开始阻塞---------");
                condition.await();
                System.out.println("阻塞结束---------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }).start();


        new Thread(() -> {
            try {
                reentrantLock.lock();
                Thread.sleep(1000);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();




//


//        Thread.sleep(Integer.MAX_VALUE);
    }
}


class LinkedBlockList<E> {

    private LinkedList<E> linkedList = new LinkedList();

    private ReentrantLock putLock = new ReentrantLock();
    private Condition notFull = putLock.newCondition();

    private ReentrantLock getLock = new ReentrantLock();
    private Condition notEmpty = getLock.newCondition();

    private int capacity = 10;
    private volatile int count = 0;

    public LinkedBlockList(int capacity) {
        this.capacity = capacity;
    }

    public void put(E e) {
        putLock.lock();
        try {
            while (capacity == count) {// 这里用while 是为了防止唤醒后如果还是满得，那么继续等待
                notFull.await();
            }
            linkedList.addFirst(e);
            count ++;
            if (count < capacity) {
                notFull.signal();
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            putLock.unlock();
        }
        if (count == 0) {
            // 这里会存在一种情况，虽然插入数据了，并且count的值也是大于1的，那么在消费端是存在消费掉元素，这个时候count有可能就是0
            notEmpty.signal();
        }
    }

    public E take(E e) {
        getLock.lock();

        E last = null;
        try {
            while (count == 0) {
                notEmpty.await();
            }
            last = linkedList.getLast();
            count--;
            if (count > 0) {
                notEmpty.signal();
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            getLock.unlock();
        }
        if (count == capacity) {
            // 如果说是满队列的，那么因为取出来一个元素所以可以通知，put阻塞线程唤醒
            notFull.signal();
        }
        return last;
    }
}

class DeadLock {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public void method1() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1000);
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public void method2() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(1000);
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}