package com.lyl.dynamicproxy;

/**
 * Client
 *
 * @author liyl
 * @date 2020-4-13
 */
public class Client {

    public static void main(String[] args) {
        // 真实对象
        Host host = new Host();

        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setRent(host);
        Rent proxy = (Rent) proxyInvocationHandler.getProxy(); // 这个Proxy就是代理生成的代理类
        proxy.rent();
    }
}
