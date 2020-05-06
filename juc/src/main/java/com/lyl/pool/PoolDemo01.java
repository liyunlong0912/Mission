package com.lyl.pool;

import java.util.concurrent.*;

/**
 *  new ThreadPoolExecutor.AbortPolicy()        抛出异常 拒绝执行 RejectedExecutionException
 *  new ThreadPoolExecutor.CallerRunsPolicy()   哪来的去哪里
 *  new ThreadPoolExecutor.DiscardPolicy()      队列满了，不会抛出异常，丢掉多余的线程
 *  new ThreadPoolExecutor.DiscardOldestPolicy() 队列满了，尝试和最早的线程去竞争，竞争成功执行，竞争失败丢弃线程，
 *  不会抛出异常
 */
public class PoolDemo01 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "OK!");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用完线程池后要关闭
            executorService.shutdown();
        }
    }
}
