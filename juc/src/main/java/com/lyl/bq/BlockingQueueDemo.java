package com.lyl.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        // 查看队首元素
        System.out.println(arrayBlockingQueue.element());

        // 抛出异常  Queue full
        // System.out.println(arrayBlockingQueue.add("d"));
        System.out.println("=====================================");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        // 跑出异常  java.util.NoSuchElementException
        // System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回值
     */
    public static void test2() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        // 查看队首元素
        System.out.println(arrayBlockingQueue.peek());

        // 不抛出异常，返回false
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println("================================");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        // 不抛出异常，有返回值 null
        System.out.println(arrayBlockingQueue.poll());
    }
    /**
     * 等待，阻塞 （一直阻塞）
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        // 一直阻塞
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");

        // 队列没有位置了，一直等待
        // arrayBlockingQueue.put("d");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());

        // 没有这个元素，一直等待
        // System.out.println(arrayBlockingQueue.take());
    }

    /**
     * 等待，阻塞 （等待超时）
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        // 等待超出2秒之后就退出
        arrayBlockingQueue.offer("d",2, TimeUnit.SECONDS);

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        // 等待超出2秒之后就退出
        System.out.println(arrayBlockingQueue.poll(2,TimeUnit.SECONDS));
    }
}
