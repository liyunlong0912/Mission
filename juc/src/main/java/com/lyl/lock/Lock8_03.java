package com.lyl.lock;

import java.util.concurrent.TimeUnit;

/**
 * Lock8
 * 5、增加两个静态的同步方法 锁住的是Class模板，先获得锁先执行
 * 6、两个对象。增加两个静态方法
 */
public class Lock8_03 {

    public static void main(String[] args) {
        Phone3 phone3 = new Phone3();

        new Thread(Phone4::sendSms, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(Phone3::call, "B").start();
    }
}


class Phone3 {

    // static 静态方法，类一加载就有了！ 锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发信息");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }
}