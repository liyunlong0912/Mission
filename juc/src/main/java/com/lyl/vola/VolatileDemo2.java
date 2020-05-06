package com.lyl.vola;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile 不保证原子性，解决方法
 */
public class VolatileDemo2 {
    private static volatile AtomicInteger num = new AtomicInteger();

    public static void add() {
        // num++;
        // AtomicInteger+1
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        // 理论上num应该为2万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
    }
}
