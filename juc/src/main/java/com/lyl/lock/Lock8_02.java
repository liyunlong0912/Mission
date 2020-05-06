package com.lyl.lock;

import java.util.concurrent.TimeUnit;

/**
 * Lock8
 * 3、新增一个普通方法后，是先发信息还是打印hello？   普通方法先执行
 * 4、两个对象，两个同步方法，先发信息还是打电话？     执行时间短的先执行
 */
public class Lock8_02 {

    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}


class Phone2 {

    // Synchronized锁的对象是方法的调用者
    // 谁先得到锁就谁先执行（两个方法用的是同一把锁）
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发信息");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    // 这里没有锁，不受同步的影响！直接执行
    public void hello() {
        System.out.println("hello");
    }

}