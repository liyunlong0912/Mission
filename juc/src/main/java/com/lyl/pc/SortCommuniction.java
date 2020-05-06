package com.lyl.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SortCommunication
 * A执行完调用B，B执行完调用C，C调用完执行A
 */
public class SortCommuniction {

    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data3.printA(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data3.printB(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data3.printC(i);
            }
        }, "C").start();
    }
}


class Data3 {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int number = 1; // 1A  2B  3C

    public void printA(int i) {
        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "==>A:" + i);
            number = 2;
            // 唤醒指定B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB(int i) {
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "==>B:" + i);
            number = 3;
            // 唤醒指定C
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int i) {
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "==>c:" + i);
            number = 1;
            // 唤醒指定A
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}