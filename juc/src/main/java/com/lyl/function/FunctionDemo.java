package com.lyl.function;

import java.util.function.Function;

/**
 * 函数型接口,有一个输入参数，一个输出参数
 * 只要是函数式接口，可以用lambda表达式简化
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 工具类
       /* Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };*/
        Function<String, String> function = str -> str;

        System.out.println(function.apply("123"));
    }
}
