package com.guo.springboot;

import java.util.concurrent.locks.LockSupport;

/**
 * @Date: 2021/4/1 9:42
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class LeetCode {
    public static void main(String[] args) {
//        Foo foo = new Foo();

//        Thread threadC = new Thread(() -> {
//            foo.third();
//        });
//
//        Thread threadB = new Thread(() -> {
//            LockSupport.park();
//            foo.second();
//            LockSupport.unpark(threadC);
//        });
//
//        Thread threadA = new Thread(() -> {
//            foo.first();
//            LockSupport.unpark(threadB);
//        });
//
//        threadA.start();
//        threadB.start();
//        threadC.start();
//====================================================
//        Thread threadA = null;
//        FooBar fooBar = new FooBar();
//        Thread finalThreadA = threadA;
//        Thread threadB = new Thread(() -> {
//            fooBar.bar(2);
//        });
//
//        threadA = new Thread(() -> {
//            fooBar.foo(2, threadB);
//        });
//        threadA.start();
//        threadB.start();
//======================================================
        Thread threadC = new Thread();

        Thread threadB = new Thread();

        Thread threadA = new Thread(() -> {

        });





    }

    public static class FooBar {
        private volatile boolean flag = false;
        public void foo(int n, Thread thread) {
             for (int i = 0; i < n; i++) {
                 while (flag) {
                     LockSupport.park();
                 }
                 flag = true;
                 System.out.print("foo");
                 LockSupport.unpark(thread);
                 LockSupport.unpark(Thread.currentThread());

              }
        }

        public void bar(int n) {
             for (int i = 0; i < n; i++) {
                 while (!flag) {
                     LockSupport.park();
                 }
                 System.out.print("bar");
                 flag = false;
             }
        }
    }

    public static class Foo {
        public void first() {
            System.out.println("first"); }
        public void second() {
            System.out.println("second");
        }
        public void third() {
            System.out.println("third");
        }
    }

    static class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(int printNumber) throws InterruptedException {
            System.out.println(printNumber);
        }

        public void even(int printNumber) throws InterruptedException {
            System.out.println(printNumber);
        }

        public void odd(int printNumber) throws InterruptedException {
            System.out.println(printNumber);
        }
    }
}
