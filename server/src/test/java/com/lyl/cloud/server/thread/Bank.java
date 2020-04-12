package com.lyl.cloud.server.thread;

/**
 * Bank
 *
 * @author liyl
 * @date 2019-12-25
 */
public class Bank {

    private int sum;
    Object obj = new Object();

    public void add(int num) {
        synchronized (obj) {
            sum = sum + num;
            System.out.println("sum=====" + sum);
        }
    }

    // public synchronized void add(int num) {
    //     sum = sum + num;
    //     System.out.println("sum=====" + sum);
    // }
}


class Customer implements Runnable {

    Bank bank = new Bank();

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            bank.add(100);
        }
    }
}

class BankDemo {

    public static void main(String[] args) {
        Customer customer = new Customer();
        Thread thread = new Thread(customer);
        Thread thread2 = new Thread(customer);
        thread.start();
        thread2.start();
    }
}

