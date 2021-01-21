package com.guo.springboot.timewheel;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2021/1/21 17:14
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class TimerMessage implements Delayed {
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
