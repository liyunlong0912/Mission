package com.lyl.cloud.server;

import java.util.Random;

/**
 * TestB
 *
 * @author liyl
 * @date 2019-12-4
 */
public class TestB {

    public static int x = new Random().nextInt(100);

    static {
        System.out.println("TestB");
    }
}
