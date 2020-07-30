package com.guo.springboot.thread;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger i = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 4, 1, SECONDS, new LinkedBlockingDeque<>(7), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        String name = "test-thread-"+i.incrementAndGet();
                        System.out.println(name);
                        Thread thread = new Thread(r,name);
                        return thread;
                    }
                });

        for (int ix = 0; ix < 10; ix++) {
            threadPoolExecutor.execute(() -> {
                try {
                    SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(threadPoolExecutor.getActiveCount());
        new Thread(() -> {
            while (true) {
                System.out.println("活动线程数"+threadPoolExecutor.getActiveCount());
                System.out.println("最大线程数"+threadPoolExecutor.getMaximumPoolSize());
                try {
                    SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        testDelayQuene();
//        testCacheThread();
    }

    private static AtomicInteger index = new AtomicInteger();
    public static void testCacheThread() {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println(index.incrementAndGet());
                String threadName = "cached thread-"+index;
                System.out.println(threadName);
                Thread thread = new Thread(r, threadName);
                return thread;
            }
        });

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("test--------------");
                    SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

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

    // 延迟队列的任务必须要实现delayed接口
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
