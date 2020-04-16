package com.lyl.staticproxy;

import com.lyl.dynamicproxy.Host;
import com.lyl.dynamicproxy.Rent;

/**
 * Proxy
 *
 * @author liyl
 * @date 2020-4-13
 */
public class Proxy implements Rent {

    private com.lyl.dynamicproxy.Host host;

    public Proxy(com.lyl.staticproxy.Host host) {

    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        host.rent();
    }

    // 看房子
    public void seeHouse() {
        System.out.println("中介带着看房子！);");
    }
}
