package com.lyl.combat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程就是一个单独的资源类，没有任何附属操作
 * 属性、方法
 */
public class SaleTickerDemo02 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 并发：多个线程操作同一个资源类  把资源类丢入线程
        Ticket2 ticket = new Ticket2(countDownLatch);

        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale(); }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
        countDownLatch.await();

    }
}

// Lock
class Ticket2 {

    private int number = 50;
    private CountDownLatch latch;

    public Ticket2(CountDownLatch latch) {
        this.latch = latch;
    }

    Lock lock = new ReentrantLock();
    public synchronized void sale() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + number-- + "票，剩余" + number + "票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            latch.countDown();
        }
    }
}