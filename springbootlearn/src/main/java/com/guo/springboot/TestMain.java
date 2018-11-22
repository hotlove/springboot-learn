package com.guo.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestMain {

    public static void main(String[] args) {


        int currentSlotIndex = 0;

        List<Task> timeWheel = new ArrayList<>(12);


    }

    public static class TimeWheel {
        // 指向当前槽
        int currentSlotIndex = 0;

        private ScheduledExecutorService scheduledExecutorService;

        public TimeWheel() {
//            scheduledExecutorService = new ScheduledThreadPoolExecutor();
        }

    }
    public static class Task implements Runnable{

        @Override
        public void run() {

        }
    }
}
