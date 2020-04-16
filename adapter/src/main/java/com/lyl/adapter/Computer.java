package com.lyl.adapter;

/**
 * Computer
 *
 * @author liyl
 * @date 2020-4-14
 */
public class Computer {

    public void net(NetToUSB netToUSB) {
        netToUSB.handler();
    }


    public static void main(String[] args) {
        Computer computer = new Computer();
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter();

        computer.net(adapter);
    }
}
