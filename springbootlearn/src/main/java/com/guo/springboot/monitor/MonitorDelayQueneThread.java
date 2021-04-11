package com.guo.springboot.monitor;

import java.util.concurrent.DelayQueue;

/**
 * @Date: 2021/2/2 9:56
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MonitorDelayQueneThread implements Runnable{

    public static DelayQueue<MonitorThreadItem> delayQueue = new DelayQueue<>();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                MonitorThreadItem monitorThreadItem = delayQueue.take();

                MonitorThreadTask task = monitorThreadItem.getTask();
                if (!task.isComplete()) {
                    // 超时时间内还有没有完成
                    // 中断该任务得执行线程
                    task.getExecuteThread().interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
