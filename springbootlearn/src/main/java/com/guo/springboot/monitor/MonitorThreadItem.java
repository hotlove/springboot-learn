package com.guo.springboot.monitor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/2/2 9:41
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MonitorThreadItem implements Delayed {

    private long time;

    private MonitorThreadTask task;

    public MonitorThreadItem(MonitorThreadTask task) {
        this.task = task;
        this.time = System.currentTimeMillis() + task.getTimeUnit().toMillis(task.getTimeout());
    }

    public MonitorThreadTask getTask() {
        return task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        MonitorThreadItem item = (MonitorThreadItem)o;
        long diff = this.time - item.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
