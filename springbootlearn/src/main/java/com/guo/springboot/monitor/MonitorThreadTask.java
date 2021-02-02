package com.guo.springboot.monitor;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/2/2 9:41
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MonitorThreadTask implements Runnable{

    private Runnable task;

    private long timeout;

    private TimeUnit timeUnit;

    private boolean complete;

    private Thread executeThread;

    public MonitorThreadTask(Runnable task, long timeout, TimeUnit timeUnit) {
        this.task = task;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public Thread getExecuteThread() {
        return executeThread;
    }

    public void setExecuteThread(Thread executeThread) {
        this.executeThread = executeThread;
    }

    public long getTimeout() {
        return timeout;
    }

    public boolean isComplete() {
        return complete;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public void run() {
        this.task.run();
        this.complete = true;
    }
}
