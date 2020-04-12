package com.lyl.cloud.server.thread;

/**
 * 卖票
 *
 * @author liyl
 * @date 2019-12-9
 */
public class TicketDemo {

    public static void main(String[] args) {
        /*Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();
        ticket1.start();
        ticket2.start();
        ticket3.start();
        ticket4.start();*/
        TicketRunnable ticketRunnable = new TicketRunnable();
        Thread t1 = new Thread(ticketRunnable);
        Thread t2 = new Thread(ticketRunnable);
        Thread t3 = new Thread(ticketRunnable);
        Thread t4 = new Thread(ticketRunnable);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}

class Ticket extends Thread {

    private static int num = 20;

    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "---sale---" + num--);

        }

    }
}

class TicketRunnable implements Runnable {

    private static int num = 50;

    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "---sale---" + num--);
            for (int i = -9000; i < 9000; i++) {
            }
        }
    }
}