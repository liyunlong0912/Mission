package com.lyl.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁） 一次只能被一个线程共有
 * 共享锁（读锁） 可以被多个线程共有
 * ReadWritLock
 * 读-读 (可以共存)
 * 读-写 (不可以共存)
 * 写-写 (不可以共存)
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        // MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp), temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }

}

/**
 * 自定义缓存
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 存、写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");

    }

    // 读、取
    public Object get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取");
        return o;
    }
}

/**
 * 自定义缓存
 * 加锁
 */
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁：更加细粒度的控制
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存、写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    // 读、取
    public Object get(String key) {
        lock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return o;
    }
}