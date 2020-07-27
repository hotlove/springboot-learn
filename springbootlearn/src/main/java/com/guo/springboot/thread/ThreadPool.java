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

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(() -> {
            System.out.println("test");
        });
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1,1, 1, SECONDS, new LinkedBlockingDeque<>());

        threadPoolExecutor.prestartAllCoreThreads();
    }
}
