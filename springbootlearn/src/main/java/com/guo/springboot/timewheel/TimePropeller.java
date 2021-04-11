package com.guo.springboot.timewheel;

import java.util.concurrent.DelayQueue;

/**
 * @Date: 2021/1/21 17:06
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class TimePropeller implements Runnable{

    public static DelayQueue<TimerMessage> delayQueue = new DelayQueue();


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                TimerMessage timerMessage = delayQueue.take();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
