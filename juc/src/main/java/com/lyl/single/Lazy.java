package com.lyl.single;

import java.lang.reflect.Constructor;

/**
 * 懒汉式单例
 */
public class Lazy {

    // 防止使用反射获取多个实例，破坏单例模式
    private static boolean flag = false;

    // 防止反射破坏单例模式
    private Lazy() {
        synchronized (Lazy.class) {
            // if (lazy != null) {
            //     throw new RuntimeException("不要试图使用反射破坏异常！");
            // }
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常！");
            }
        }
    }

    private static volatile Lazy lazy;

    // 双重检查锁（DCL饿汉式单例）
    public static Lazy getInstance() {
        if (null == lazy) {
            synchronized (Lazy.class) {
                if (null == lazy) {
                    lazy = new Lazy(); //不是原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向内存空间
                     *
                     * 123
                     * 132 A
                     *     B //此时是一个只有内存地址没有实例的空对象
                     */
                }
            }
        }
        return lazy;
    }

    public static void main(String[] args) throws Exception {
        // Lazy instance = Lazy.getInstance();
        Constructor<Lazy> constructor = Lazy.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Lazy instance = constructor.newInstance();
        Lazy lazy = constructor.newInstance();
        System.out.println(instance.hashCode());
        System.out.println(lazy.hashCode());
    }
}
