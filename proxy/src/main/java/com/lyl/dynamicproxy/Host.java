package com.lyl.dynamicproxy;

/**
 * Host 房东
 *
 * @author liyl
 * @date 2020-4-13
 */
public class Host implements Rent {


    @Override
    public void rent() {
        System.out.println("房东要出租房子！");
    }
}
