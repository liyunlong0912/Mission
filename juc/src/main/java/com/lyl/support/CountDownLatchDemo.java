package com.lyl.support;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * 计数器
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(5);
        for (int i = 1; i <=5 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"出去了");
                // 计数-1
                count.countDown();
            }, String.valueOf(i)).start();
        }
        count.await();
        System.out.println("所有人走完了！");
    }
}
