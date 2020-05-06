package com.lyl.support;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 */
public class CyclicBarrierDeme {

    public static void main(String[] args) {
        /**
         *  集齐7龙珠召唤神龙
         *
         */
        // 召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("召唤神龙！"));
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集了" + temp + "颗龙珠");
                try {
                    // 计数，等待，计数达到7后，召唤神龙（穿过屏障）
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
