package com.guo.springboot.thread;

import java.util.concurrent.*;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/24 18:28
 * @Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        MonitorThreadPoolExecutor monitorThreadPoolExecutor =
                new MonitorThreadPoolExecutor(2, 2, 0,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(8));

        for (int i = 0; i < 5; i++) {
            monitorThreadPoolExecutor.execute(() -> {
                long sartTime = System.currentTimeMillis();
                try {
//                    System.out.println("任务开始执行------");
                    System.out.println("线程:"+Thread.currentThread().getName()+"执行了超时任务");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("线程:"+Thread.currentThread().getName()+"任务中断"+(System.currentTimeMillis() - sartTime));
                }
            }, 2, TimeUnit.SECONDS);
        }



        new Thread(() -> {
            while (true) {
                monitorThreadPoolExecutor.execute(() -> {
                    System.out.println("线程:"+Thread.currentThread().getName()+"执行了任务");
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(8));
//
//        for (int i = 0; i < 10; i++) {
//            Future<?> future = threadPoolExecutor.submit(() -> {
//                long sartTime = System.currentTimeMillis();
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    System.out.println("任务中断" + (System.currentTimeMillis() - sartTime));
//                }
//            });
//            try {
//                future.get(2, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//                future.cancel(true);
//            }
//        }

    }
}
