package com.lyl.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * <p>
 * 使用ForkJoin
 * 1、使用ForkJoinPool 来执行
 * 2、计算任务 forkjoinpool.execute(ForkJoinTask<?> task)
 * 3、计算类要继承ForkJoinTask (RecursiveTask)
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private Long temp = 10000L;
    long sum;


    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < temp) {
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            // 拆分任务，把任务压入线程队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
