package com.guo.springboot.monitor;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/2/2 9:53
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

        MonitorThreadPoolExecutor threadPoolExecutor
                = new MonitorThreadPoolExecutor(3, 3, 0,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10));

        threadPoolExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("任务被中断1"+Thread.currentThread().getName());
            }
        }, 3, TimeUnit.SECONDS);
        threadPoolExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("任务被中断2"+Thread.currentThread().getName());
            }
        }, 3, TimeUnit.SECONDS);

        Future<?> submit = threadPoolExecutor.submit(() -> {

        });
    }
}
