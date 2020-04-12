package com.lyl.cloud.server;

/**
 * TestA
 *
 * @author liyl
 * @date 2019-12-4
 */
public class TestA {

    public static int x = 6 / 3;
    public static final int y = 6 / 3;

    static {
        System.out.println("TestA");
        x = 5;
    }
}
