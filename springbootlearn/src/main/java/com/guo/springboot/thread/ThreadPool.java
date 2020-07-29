package com.guo.springboot.thread;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Date: 2020/7/22 15:30
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.execute(() -> {
//            System.out.println("test");
//        });
//        ThreadPoolExecutor threadPoolExecutor =
//                new ThreadPoolExecutor(4, 5, 1, SECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        Thread thread = new Thread(r,"test-thread");
//                        thread.start();
//                        return thread;
//                    }
//                });
//
//        int i = threadPoolExecutor.prestartAllCoreThreads();
//        System.out.println(i);
        testDelayQuene();
    }

    public static void testDelayQuene() throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.put(new DelayTask("task1",5, SECONDS));
        delayQueue.put(new DelayTask("task2",5, SECONDS));
        delayQueue.put(new DelayTask("task3",5, SECONDS));

        for (int i = 0; i < 3; i++) {
            DelayTask take = (DelayTask) delayQueue.take();
            System.out.println(take.toString());
        }
    }

    static class DelayTask implements Delayed {

        private long time;
        private String name;

        public DelayTask(String name, long time, TimeUnit unit) {
            this.name = name;
            this.time = System.currentTimeMillis() + (time > 0? unit.toMillis(time): 0);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            DelayTask item = (DelayTask) o;
            long diff = this.time - item.time;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            }else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return "Item{" +
                    "time=" + time +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
