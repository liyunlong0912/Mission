package com.lyl.cloud.ribbon.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * @author liyl
 * @date 2019-10-22
 */
public class HelloServiceCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    /*
     protected HelloServiceCommand(HystrixCommandGroupKey group) {
         super(group);
     }
    */

    protected HelloServiceCommand(String commandGroupKey, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }

    /* 服务调用 */
    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();
    }

    @Override
    protected String getFallback() {
        return "error";
    }

    // Hystrix的缓存
    @Override
    protected String getCacheKey() {

        //一般动态的取缓存Key,比如userId，这里为了做实验写死了，写为hello
        return "hello";
    }
}
