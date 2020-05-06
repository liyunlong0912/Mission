package com.lyl.function;

import java.util.function.Consumer;

/**
 * Consumer 消费式接口  只有输入值  没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        // Consumer<String> consumer = new Consumer<String>() {
        //     @Override
        //     public void accept(String str) {
        //         System.out.println(str);
        //     }
        // };
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("aaa");
    }
}
