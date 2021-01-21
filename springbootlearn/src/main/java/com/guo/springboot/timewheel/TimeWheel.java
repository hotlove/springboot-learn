package com.guo.springboot.timewheel;

import io.reactivex.internal.operators.flowable.FlowableTake;
import org.apache.kafka.common.utils.Time;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/1/21 16:34
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class TimeWheel {

    private long tickTime;

    private long wheelSize;

    private long currentPos = 0L;

    private TimeUnit timeUnit;

    private TimerTaskList[] wheel;

    public TimeWheel(long tickTime, TimeUnit timeUnit, int wheelSize) {

        wheel = new TimerTaskList[wheelSize];
        wheelSize = wheelSize;
        tickTime = tickTime;
        timeUnit = timeUnit;
        currentPos = Time.SYSTEM.hiResClockMs();
    }

    public void execute(long delay, TimeUnit timeUnit, Runnable task) {
        // 桶索引
        long bucketIndex = (delay + currentPos) % wheelSize ;

        if (timeUnit.compareTo(this.timeUnit) != 0) {
            throw new IllegalArgumentException("TimeUnit 不合法");
        }

        if (timeUnit.toMillis(delay) == this.timeUnit.toMillis(this.currentPos)) {
            new Thread(() -> {
                // 立即执行
                task.run();
            }).start();
        }

        TimerTask timerTask = new TimerTask();
        timerTask.setTask(task);
        timerTask.setTimeUnit(timeUnit);
        timerTask.setDelay(delay);

        TimerTaskList timerTaskList = wheel[(int) bucketIndex];
        timerTaskList.addNext(timerTask);

        // 利用delayquene推进cusPos
        TimePropeller.delayQueue.add(new TimerMessage());

    }
}
