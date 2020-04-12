package com.lyl.cloud.server.thread;

/**
 * ThreadDemo3
 *
 * @author liyl
 * @date 2019-12-6
 */
public class ThreadDemo3 {

    public static void main(String[] args) {

        // Demo d1 = new Demo("旺财");
        // Demo d2 = new Demo("小青");
        // d1.start();
        // d2.start();
        // for (int x = 0; x < 10; x++) {
        //     System.out.println("...x=" + x + "...name=" + Thread.currentThread().getName());
        // }

        Thread thread = new Thread(new Demo2());
        Thread thread1 = new Thread(new Demo2());
        thread.start();
        thread1.start();

    }
}

class Demo extends Thread {

    private String name;

    public Demo(String name) {
        super(name);
        // this.name = name;
    }

    @Override
    public void run() {
        // super.run();
        for (int x = 0; x < 20; x++) {
            System.out.println("...x=" + x + "...name=" + Thread.currentThread().getName());
        }
    }
}

/**
 * 创建线程的方法
 */
class Demo2 implements Runnable{

    @Override
    public void run() {
        for (int x = 0; x < 20; x++) {
            System.out.println("...x=" + x + "...name=" + Thread.currentThread().getName());
        }
    }
}

