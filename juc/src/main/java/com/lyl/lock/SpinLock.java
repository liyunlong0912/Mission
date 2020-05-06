package com.lyl.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁测试
 */
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference();

    // 加锁
    public void MyLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "=======>myLock");
        // 自旋锁
        while (atomicReference.compareAndSet(null, thread)) {
        }
    }

    // 解锁
    public void UnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "=======>unLock");
        while (atomicReference.compareAndSet(thread, null)) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.MyLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.getMessage();
            } finally {
                spinLock.UnLock();
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            spinLock.MyLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.getMessage();
            } finally {
                spinLock.UnLock();
            }
        }, "B").start();
    }
}
