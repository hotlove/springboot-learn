package com.guo.springboot.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @Date: 2020/10/26 19:41
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ThreadMonitorPoolExecutor extends ThreadPoolExecutor {

    private ConcurrentHashMap<Thread, ThreadMonitorTask> taskRunTimeManager = new ConcurrentHashMap<>();

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ThreadMonitorPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue, Long period, TimeUnit timeUnit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        if (period != null) {
            this.startMonitor(period, timeUnit != null ? timeUnit : TimeUnit.SECONDS);
        }
    }

    public ThreadMonitorPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory, Long period, TimeUnit timeUnit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        if (period != null) {
            this.startMonitor(period, timeUnit != null ? timeUnit : TimeUnit.SECONDS);
        }

    }

    public ThreadMonitorPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                     RejectedExecutionHandler handler, Long period, TimeUnit timeUnit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        if (period != null) {
            this.startMonitor(period, timeUnit != null ? timeUnit : TimeUnit.SECONDS);
        }
    }

    public ThreadMonitorPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory, RejectedExecutionHandler handler, Long period, TimeUnit timeUnit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        if (period != null) {
            this.startMonitor(period, timeUnit != null ? timeUnit : TimeUnit.SECONDS);
        }
    }

    /**
     * 启动监控线程
     */
    private void startMonitor(long period, TimeUnit timeUnit) {
        // 监控线程
        Runnable monitorThread = () -> {
            Iterator<Thread> iterator = taskRunTimeManager.keySet().iterator();
            while (iterator.hasNext()) {
                Thread executeThread = iterator.next();

                ThreadMonitorTask threadMonitorTask = taskRunTimeManager.get(executeThread);

                if (threadMonitorTask.releaseThread) {
                    // 说明任务已经执行完毕
                    continue;
                }
                long currentTime = System.currentTimeMillis();
                if (currentTime > threadMonitorTask.killTime) {
                    taskRunTimeManager.remove(executeThread);
                    executeThread.interrupt();
                }
            }
        };

        scheduledExecutorService.scheduleAtFixedRate(monitorThread, 0, period, timeUnit);
    }



    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof ThreadMonitorTask) {
            ThreadMonitorTask threadMonitorTask = (ThreadMonitorTask) r;
//            threadMonitorTask.monitorExcuteThread(t);
            long startTime = System.currentTimeMillis();
            long time = threadMonitorTask.timeUnit.toMillis(threadMonitorTask.timeout);
            threadMonitorTask.killTime = time + startTime;
            taskRunTimeManager.put(t, threadMonitorTask);
        }
    }

    public void execute(Runnable task, long timeout, TimeUnit timeUnit) {
        ThreadMonitorTask threadMonitorTask = new ThreadMonitorTask(task, timeout, timeUnit);
        super.execute(threadMonitorTask);
    }

    public static class ThreadMonitorTask implements Runnable{

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        private Runnable task;

        private long timeout;

        private TimeUnit timeUnit;

        private long killTime;

        private boolean releaseThread = false;

        public long getKillTime() {
            return killTime;
        }

        public void setKillTime(long killTime) {
            this.killTime = killTime;
        }

        public ThreadMonitorTask(Runnable runnable, long timeout, TimeUnit timeUnit) {
            this.task = runnable;
            this.timeout = timeout;
            this.timeUnit = timeUnit;
        }

        public void monitorExcuteThread(Thread thread) {
            new Thread(() -> {
                try {
                    // 这里监控执行线程执行线程如果执行过任务还有释放线程，则说明线程超时中断本次操作
                    this.timeUnit.sleep(this.timeout);
                    if (!releaseThread) {
                        // 如果本次线程都执行完了就没必要进行中断了
                        thread.interrupt();
                        logger.info("线程:"+Thread.currentThread().getName()+"执行任务中断");
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
            logger.info("任务执行耗时:" + (currentTime - sartTaskTime));
            // 如果线程执行没有超过指定的执行时间
            if ((currentTime - sartTaskTime) <= this.timeUnit.toMillis(this.timeout)) {
                this.releaseThread = true;
            }
        }
    }
}
