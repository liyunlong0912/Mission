package com.lyl.unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * SetTest
 *
 */
public class SetTest {

    public static void main(String[] args) {

        /**
         * 解决方法
         * 1、Collections.synchronizedSet(new HashSet<>());
         * 2、Set<String> strings = new CopyOnWriteArraySet<>();
         */
        Set<String> strings = new HashSet<>();

        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }
}
