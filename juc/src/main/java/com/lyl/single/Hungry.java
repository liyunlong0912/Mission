package com.lyl.single;

/**
 *  饿汉式单例
 */
public class Hungry {

    private Hungry() {

    }

    // 可能会浪费内存空间
    private byte[] bytes = new byte[1024 * 1024];
    private byte[] bytes1 = new byte[1024 * 1024];
    private byte[] bytes2 = new byte[1024 * 1024];
    private byte[] bytes3 = new byte[1024 * 1024];

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }

}
