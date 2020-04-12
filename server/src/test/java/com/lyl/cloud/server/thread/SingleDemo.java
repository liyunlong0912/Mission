package com.lyl.cloud.server.thread;

/**
 * 多线程下的单例
 *
 * @author liyl
 * @date 2019-12-25
 */
public class SingleDemo {


}


// 饿汉式
class Single {

    private static final Single SINGLE = new Single();

    public Single() {
    }

    public static Single getInstance() {
        return SINGLE;
    }
}

// 懒汉式->(双重检查锁)
class Single1 {
    private static Single1 single1 = null;

    public Single1() {
    }

    public static Single1 getInstance() {
        if (single1 == null) {
            synchronized (Single1.class) {
                if (single1 == null) {
                    single1 = new Single1();
                }
            }
        }
        return single1;
    }

}

