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

//    ConcurrentHashMap<Thread, MonitorTask> taskRunTimeManager = new ConcurrentHashMap<>();
//
//    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//
//    volatile int state = 0;

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

//    Runnable monitorThread = () -> {
//        Iterator<Thread> iterator = taskRunTimeManager.keySet().iterator();
//        while (iterator.hasNext()) {
//            Thread executeThread = iterator.next();
//            MonitorTask monitorTask = taskRunTimeManager.get(executeThread);
//
//            long startTime = monitorTask.getStartTime();
//            long currentTime = System.currentTimeMillis();
//            if ((currentTime - startTime) > monitorTask.getTimeout()) {
//                taskRunTimeManager.remove(executeThread);
//                executeThread.interrupt();
//            }
//        }
//    };

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        // 现在计时有误差，希望到点自己自动释放
        if (r instanceof MonitorTask) {
            MonitorTask monitorTask = (MonitorTask) r;
//            monitorTask.setStartTime(System.currentTimeMillis());
//            taskRunTimeManager.put(t, monitorTask);
            monitorTask.monitorTaskTime(t);
        }
    }

    public void execute(Runnable command, long timeout, TimeUnit timeUnit) {
        if (command == null) {
            throw new NullPointerException();
        }

        MonitorTask monitorTask = new MonitorTask(command);
        monitorTask.setTimeout(timeout);
        monitorTask.setTimeUnit(timeUnit);

        super.execute(monitorTask);
    }

    public static class MonitorTask implements Runnable {
        private Runnable task = null;

        private long timeout;

        private long startTime;

        private TimeUnit timeUnit;

        private boolean releaseThread = false;

        public MonitorTask(Runnable task) {
            this.task = task;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }

        public void setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }

        public void monitorTaskTime(Thread thread) {
            new Thread(() -> {
                try {
                    // 这里监控执行线程执行线程如果执行过任务还有释放线程，则说明线程超时中断本次操作
                    this.timeUnit.sleep(this.timeout);
                    if (!releaseThread) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        @Override
        public void run() {
            long sartTaskTime = System.currentTimeMillis();
            this.task.run();
            long currentTime = System.currentTimeMillis();
            if ((currentTime - sartTaskTime) < this.timeUnit.toMillis(this.timeout)) {
                this.releaseThread = true;
            }
        }
    }

}
