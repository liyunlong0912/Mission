package com.lyl.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁测试
 * Lock
 */
public class CrsDemo1 {
    public static void main(String[] args) {

        CrsPhone1 crsPhone = new CrsPhone1();
        new Thread(() -> {
            crsPhone.sms();
        }, "A").start();


        new Thread(() -> {
            crsPhone.sms();
        }, "B").start();
    }
}


class CrsPhone1 {

    Lock lock = new ReentrantLock();

    public void sms() {
        // lock必须成对出现，否则会出现死锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "sms");
            // 这里也有锁
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}