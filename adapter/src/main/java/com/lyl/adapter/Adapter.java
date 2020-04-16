package com.lyl.adapter;

/**
 * Adapter
 *
 * @author liyl
 * @date 2020-4-14
 */
public class Adapter  extends Adaptee implements NetToUSB{

    @Override
    public void handler() {
        super.request();
    }
}
