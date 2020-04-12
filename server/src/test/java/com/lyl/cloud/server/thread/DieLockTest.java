package com.lyl.cloud.server.thread;

/**
 * DieLockTest
 *
 * @author liyl
 * @date 2019-12-25
 */
public class DieLockTest {

    public static void main(String[] args) {
        Test a = new Test(true);
        Test b = new Test(false);

        Thread t = new Thread(a);
        Thread tb = new Thread(b);
        t.start();
        tb.start();
    }

}


class Test implements Runnable {

    private boolean flag;

    public Test(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {

            synchronized (MyLock.locka) {
                System.out.println("if----------locka.....");
                synchronized (MyLock.lockb) {
                    System.out.println("if----------lockb.....");
                }
            }
        } else {
            synchronized (MyLock.lockb) {
                System.out.println("else----------lockb.....");
                synchronized (MyLock.locka) {
                    System.out.println("else----------locka.....");
                }
            }
        }
    }
}

class MyLock {
    public static final Object locka = new Object();
    public static final Object lockb = new Object();
}

