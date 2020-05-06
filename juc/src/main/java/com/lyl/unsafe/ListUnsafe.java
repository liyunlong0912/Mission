package com.lyl.unsafe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException 并发修改异常
public class ListUnsafe {

    public static void main(String[] args) {

        // 并发下，List不安全
        /**
         * 解决方案:
         * 1、Vector
         * 2、Collections.synchronizedList(new ArrayList<>())
         * 3、List<String> strings = new CopyOnWriteArrayList<>();
         *
         */

        // CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化策略
        // 多的线程调用的时候 list不安全，读取的时候是固定的，写入时会出现覆盖现象
        // 写入的时候复制一份，避免覆盖
        // 读写分离
        // CopyOnWriteArrayList 底层用的是Lock 效率比较高
        List<String> strings = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 20; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }
}
