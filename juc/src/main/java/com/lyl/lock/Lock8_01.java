package com.lyl.lock;

import java.util.concurrent.TimeUnit;

/**
 * Lock8
 * 8锁就是关于锁的8的问题
 * 1、标准情况下，两个线程是先打电话还是发信息？        1/发信息 2/打电话
 * 2、sendSms延迟四秒，两个线程是先打电话还是发信息？   1/发信息 2/打电话
 */
public class Lock8_01 {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}


class Phone {

    // Synchronized锁的对象是方法的调用者
    // 谁先得到锁就谁先执行（两个方法用的是同一把锁）
    public synchronized void sendSms() {
        System.out.println("发信息");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}