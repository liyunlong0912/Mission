package com.lyl.lock;

import java.util.concurrent.TimeUnit;

/**
 * Lock8
 * 7、一个静态同步方法，一个普通同步方法，一个对象             两把锁，时间短的先执行
 * 8、一个静态同步方法，一个普通同步方法，两个对象
 */
public class Lock8_04 {

    public static void main(String[] args) {
        Phone4 phone4 = new Phone4();

        new Thread(Phone4::sendSms, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone4::call, "B").start();
    }
}


class Phone4 {

    // static 静态方法，类一加载就有了！ 锁的是Class
    public static synchronized void sendSms() {
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
}