package com.lyl.unsafe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MapTest
 */
public class MapTest {
    public static void main(String[] args) {
        // map是这样用的么？不是，工作中不这么用hashmap
        // 默认等价什么？  new HashMap<String, String>(16,0.75f)
        // Collections.synchronizedMap()

        Map<String, String> map = new ConcurrentHashMap<String, String>();
        // 加载因子、初始容量
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
