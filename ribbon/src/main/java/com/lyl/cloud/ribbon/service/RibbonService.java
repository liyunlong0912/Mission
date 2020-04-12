package com.lyl.cloud.ribbon.service;

import rx.Observable;

import java.util.concurrent.ExecutionException;

/**
 * @author liyl
 * @date 2019-10-21
 */
public interface RibbonService {

    String helloService() throws Exception;

    Observable<String> hiService() throws ExecutionException, InterruptedException;
}
