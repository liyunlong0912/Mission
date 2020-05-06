package com.lyl.function;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是布尔类型值
 */
public class PredicateDemo {
    public static void main(String[] args) {
        // 判断字符串是否为空
        // Predicate<String> predicate = new Predicate<String>() {
        //     @Override
        //     public boolean test(String s) {
        //         return s.isEmpty();
        //     }
        // };

        Predicate<String> predicate = s -> s.isEmpty();

        System.out.println(predicate.test("245"));
    }
}
