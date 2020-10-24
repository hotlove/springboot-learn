package com.guo.springboot.thread;

import sun.awt.SunHints;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/10/24 17:50
 * @Description:
 */
public class MonitorThreadPoolExecutor extends ThreadPoolExecutor {

    ConcurrentHashMap<Future, Long> taskRunTimeManager = new ConcurrentHashMap<>();

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    volatile int state = 0;

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    Runnable monitorThread = () -> {
        Iterator<Future> iterator = taskRunTimeManager.keySet().iterator();
        System.out.println("队列大小"+this.getQueue().size());
        System.out.println("活跃线程数"+this.getActiveCount());
        while (iterator.hasNext()) {
            Future key = iterator.next();
            Long aLong = taskRunTimeManager.get(key);
            long current = System.currentTimeMillis();
            if (current > aLong.longValue()) {
                key.cancel(true);
                taskRunTimeManager.remove(key);
            }
        }
    };

    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        super.execute(command);
    }

    public void execute(Runnable command, long timeout) {
        if (command == null) {
            throw new NullPointerException();
        }

        Future<?> submit = super.submit(command);
        taskRunTimeManager.put(submit, System.currentTimeMillis() + timeout * 1000);
        if (state == 0) {
            scheduledExecutorService.scheduleAtFixedRate(monitorThread, 0, 400, TimeUnit.MILLISECONDS);
            state = 1;
        }
    }
}
