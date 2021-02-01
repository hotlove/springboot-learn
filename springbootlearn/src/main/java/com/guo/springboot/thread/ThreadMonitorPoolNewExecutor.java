package com.guo.springboot.thread;

import java.util.concurrent.*;

/**
 * @Date: 2021/2/1 17:08
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ThreadMonitorPoolNewExecutor extends ThreadPoolExecutor {

    public ThreadMonitorPoolNewExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadMonitorPoolNewExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadMonitorPoolNewExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadMonitorPoolNewExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        new Thread(new DelayMonitorThread()).start();
    }

    /**
     *1.这里不再使用定时任务，那样会有很多得空轮询
     * 2.不使用阻塞线程会造成很多得线程资源浪费
     * 3.这里使用Java自带delayQuene实现延迟效果，超时时间为延迟得时间，在延迟时间到达后判断是否任务执行完，
     * 如果执行完则忽略，如果没有执行完则进行中断，注意这里只针对，可以被中断得线程，对中断无响应得不行
     */
    public static DelayQueue<MonitorDelayItem> delayQueue = new DelayQueue<>();

    public static class DelayMonitorThread implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    MonitorDelayItem take = delayQueue.take();

                    MonitorTask monitorTask = take.getMonitorTask();
                    if (!monitorTask.releaseThread) {
                        // 中断县城
                        monitorTask.getExecuteThread().interrupt();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        // 现在计时有误差，希望到点自己自动释放
        if (r instanceof ThreadMonitorPoolNewExecutor.MonitorTask) {
            ThreadMonitorPoolNewExecutor.MonitorTask monitorTask = (ThreadMonitorPoolNewExecutor.MonitorTask) r;

            monitorTask.setExecuteThread(t);
            delayQueue.offer(new MonitorDelayItem(monitorTask));
        }
    }

    public void execute(Runnable command, long timeout, TimeUnit timeUnit) {
        if (command == null) {
            throw new NullPointerException();
        }

        ThreadMonitorPoolNewExecutor.MonitorTask monitorTask = new ThreadMonitorPoolNewExecutor.MonitorTask(command);
        monitorTask.setTimeout(timeout);
        monitorTask.setTimeUnit(timeUnit);

        super.execute(monitorTask);
    }



    public static class MonitorDelayItem implements Delayed {

        private MonitorTask monitorTask;

        private long time;

        public MonitorTask getMonitorTask() {
            return monitorTask;
        }

        public void setMonitorTask(MonitorTask monitorTask) {
            this.monitorTask = monitorTask;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public MonitorDelayItem(MonitorTask monitorTask) {
            this.monitorTask = monitorTask;
            this.time = System.currentTimeMillis() + (monitorTask.getTimeUnit().toMillis(monitorTask.getTimeout()));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return this.time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            MonitorDelayItem item = (MonitorDelayItem) o;
            long diff = this.time - item.getTime();
            if (diff <= 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static class MonitorTask implements Runnable {
        private Runnable task = null;

        private long timeout;

        private long startTime;

        private TimeUnit timeUnit;

        private boolean releaseThread = false;

        private Thread executeThread;

        public Thread getExecuteThread() {
            return executeThread;
        }

        public void setExecuteThread(Thread executeThread) {
            this.executeThread = executeThread;
        }

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
            if ((currentTime - sartTaskTime) <= this.timeUnit.toMillis(this.timeout)) {
                this.releaseThread = true;
            }
        }
    }
}
