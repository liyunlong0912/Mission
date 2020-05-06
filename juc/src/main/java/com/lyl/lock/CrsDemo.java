package com.lyl.lock;

/**
 * 重入锁测试
 *  Synchronized
 */
public class CrsDemo {
    public static void main(String[] args) {

        CrsPhone crsPhone = new CrsPhone();
        new Thread(() -> {
            crsPhone.sms();
        }, "A").start();


        new Thread(() -> {
            crsPhone.sms();
        }, "B").start();
    }
}


class CrsPhone {

    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "sms");
        // 这里也有锁
        call();
    }

    public synchronized void call() {

        System.out.println(Thread.currentThread().getName() + "sms");

    }

}