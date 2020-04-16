package com.lyl.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyInvocationHandler
 *
 * @author liyl
 * @date 2020-4-13
 */
// 用这个类生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public Rent setRent(Rent rent) {
        return this.rent= rent;
    }

    // 生成得到代理类
    public Object getProxy() {
        return  Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    // 处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 动态代理的本质，就是用反射机制实现！
        Object result = method.invoke(rent, args);
        return result;
    }
}
