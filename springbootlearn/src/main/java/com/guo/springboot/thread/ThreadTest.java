package com.guo.springboot.thread;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/24 18:28
 * @Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
//        ThreadMonitorPoolExecutor monitorThreadPoolExecutor =
//                new ThreadMonitorPoolExecutor(2, 2, 0,
//                        TimeUnit.SECONDS,
//                        new LinkedBlockingDeque<>(8), 1L, TimeUnit.MINUTES);
//
//        for (int i = 0; i < 5; i++) {
//            monitorThreadPoolExecutor.execute(() -> {
//                long sartTime = System.currentTimeMillis();
//                try {
////                    System.out.println("任务开始执行------");
//                    System.out.println("线程:"+Thread.currentThread().getName()+"执行了超时任务");
//                    TimeUnit.MINUTES.sleep(7);
//                } catch (InterruptedException e) {
////                    e.printStackTrace();
//                    System.out.println("线程:"+Thread.currentThread().getName()+"任务中断"+(System.currentTimeMillis() - sartTime));
//                }
//            }, 5, TimeUnit.MINUTES);
//        }
//
//        Future<?> test = monitorThreadPoolExecutor.submit(() -> {
//            System.out.println("test");
//        });
//
//        try {
//            test.get(1L, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//            test.cancel(true);
//        }


//        new Thread(() -> {
//            while (true) {
//                monitorThreadPoolExecutor.execute(() -> {
//                    System.out.println("线程:"+Thread.currentThread().getName()+"执行了任务");
//                });
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();

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
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                msg(finalI);
            }).start();

        }
    }

    public static void msg(Integer integer) {
        int i = 0;
        i++;
        Map<Integer, Object> msg = new HashMap<>();
        msg.put(integer, "value" + integer);
        System.out.println(JSON.toJSONString(msg));
        System.out.println(i);
    }
}
