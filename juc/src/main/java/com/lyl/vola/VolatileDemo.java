package com.lyl.vola;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    // 不加Volatile,程序就会死循环
    // 加Volatile保证可见性
    private static volatile int number = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (number == 0) {
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        number++;
        System.out.println(number);
    }
}
