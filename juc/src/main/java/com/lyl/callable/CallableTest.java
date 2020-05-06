package com.lyl.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CallableTest
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // new Thread().start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<Callable>()).start();

        MyThread myThread = new MyThread();
        // 适配类
        FutureTask task = new FutureTask(myThread);
        new Thread(task, "A").start();
        new Thread(task, "B").start();
        /*正常情况下两个线程会有两个结果，如果两个结果一样，只会返回一个；
        原因：结果会被缓存，提高效率*/
        // 获取callable返回结果,这个get方法可能会产生阻塞，或者用异步通信处理
        String o = (String) task.get();
        System.out.println(o);
    }

}

class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("启用call()");
        return "1024";
    }
}
