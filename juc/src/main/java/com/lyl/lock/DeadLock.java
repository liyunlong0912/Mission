package com.lyl.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DeadLock
 */
public class DeadLock {

    public static void main(String[] args) {
        String LockA = "LockA";
        String LockB = "LockB";
        new Thread(new MyThread(LockA, LockB), "T1").start();
        new Thread(new MyThread(LockB, LockA), "T2").start();
    }
}

class MyThread implements Runnable {

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    Lock lock = new ReentrantLock();

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "lock" + lockA + ",get++++" + lockB);
            TimeUnit.SECONDS.sleep(2);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "lock" + lockB + ",get++++" + lockA);
            }
        }
    }
}
