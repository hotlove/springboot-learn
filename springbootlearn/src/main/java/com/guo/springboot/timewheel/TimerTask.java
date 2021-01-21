package com.guo.springboot.timewheel;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/1/21 16:40
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class TimerTask {

    public TimerTask prev;

    public TimerTask next;

    private Runnable task;

    private long delay;

    private TimeUnit timeUnit;

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
