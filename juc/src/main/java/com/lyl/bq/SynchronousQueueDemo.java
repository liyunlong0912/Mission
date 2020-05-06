package com.lyl.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 和其他的BlockingQueue不同，SynchronousQueue不存储元素
 *  put一个元素，必须take出来，否则无法put进去
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        // 同步队列
        BlockingQueue<String> synchronousQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() + "取出" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() + "取出" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() + "取出" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

    }
}
