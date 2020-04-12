package com.lyl.cloud.ribbon.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @author liyl
 * @date 2019-10-22
 */
public class HelloServiceObserveCommand extends HystrixObservableCommand<String> {

    private RestTemplate restTemplate;

    protected HelloServiceObserveCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    public HelloServiceObserveCommand(String commandGroupKey, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<String> construct() {
        // 观察者订阅网络请求事件
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    System.out.println("方法执行。。。");
                    String body = restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();
                    // 这个方法是监听方法，是传递结果的，请求多次的结果通过它返回去汇总起来。
                    subscriber.onNext(body);
                    String body1 = restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();
                    subscriber.onNext(body1);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    // 服务降级FallBack
    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("error");
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
