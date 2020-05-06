package com.lyl.furture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用
 * 1、异步执行
 * 2、成功回调
 * 3、失败回调
 */
public class FurtureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 没有返回值
       /* CompletableFuture<Void> objectCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync==》Void");
        });
        System.out.println("11111");
        // 获取阻塞执行结果
        objectCompletableFuture.get();*/

        // 有返回值
        CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "runAsync==》Void");
            int i = 10 / 0;
            return 1024;
        });

        System.out.println(objectCompletableFuture.whenComplete((u, t) -> {
            System.out.println("u===" + u); //正常的返回结果
            System.out.println("t===" + t); // 错误信息 java.util.concurrent.CompletionException: java.lang
            // .ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;
        }).get());
    }
}
