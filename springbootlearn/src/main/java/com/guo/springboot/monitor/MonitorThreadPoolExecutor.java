package com.guo.springboot.monitor;

import java.util.concurrent.*;

/**
 * @Date: 2021/2/2 9:27
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MonitorThreadPoolExecutor extends ThreadPoolExecutor{

    private static final RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, defaultHandler);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), handler);
    }

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
//        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        // 启动一个定时检测超时线程
        new Thread(new MonitorDelayQueneThread()).start();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof MonitorThreadTask) {
            MonitorThreadTask task = (MonitorThreadTask) r;
            task.setExecuteThread(t);
            MonitorDelayQueneThread.delayQueue.offer(new MonitorThreadItem(task));
        }
    }

    public void execute(Runnable task, long timeout, TimeUnit timeUnit) {
        super.execute(new MonitorThreadTask(task, timeout, timeUnit));
    }

    public void execute(Runnable task) {
        super.execute(task);
    }
}
