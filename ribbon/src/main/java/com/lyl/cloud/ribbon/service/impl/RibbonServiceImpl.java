package com.lyl.cloud.ribbon.service.impl;

import com.lyl.cloud.ribbon.service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author liyl
 * @date 2019-10-21
 */
@Service
public class RibbonServiceImpl implements RibbonService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    // 请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() throws Exception {

        // restTemplate.getForEntity()阻塞方式请求
        // return restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();


        /*非阻塞式IO有两个分别是：Future将来式，Callable回调式
         *1.Future将来式：就是说你用Future将来式去请求一个网络IO之类的任务，它会以多线程的形式去实现，主线程不必卡死在哪里等待，
         *  等什么时候需要结果就通过Future的get()方法去取，不用阻塞。
         *2.Callable回调式：预定义一个回调任务，Callable发出去的请求，主线程继续往下执行，等你请求返回结果执行完了，
         *  会自动调用你哪个回调任务。
         */
        Future<String> result = new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();
            }
        };
        return result.get();
    }

    // 多请求结果会聚的注解写法，调用还是跟手写会聚一样调用
    // ObservableExecutionMode.EAGER热执行  ObservableExecutionMode.LAZY冷执行
    // 还可以忽略某些异常避免出现服务降级，有时候某些异常出现，但是我们并不想服务降级，异常就异常吧。参数ignoreExceptions = XXX.class
    // groupKey ="" ,threadPoolKey = "",这是线程隔离，比如我需要根据groupKey划分，如果还要对groupKey内的任务进一步划分，就要threadPoolKey，比如对groupKey组内进行
    // 读取数据的时候，是从缓存读，还是数据库读
    // @CacheKey,缓存的注解方式
    @Override
    @HystrixCommand(fallbackMethod = "helloFallBack", observableExecutionMode = ObservableExecutionMode.LAZY)
    public Observable<String> hiService() throws ExecutionException, InterruptedException {

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

    public String helloFallBack(Throwable throwable) {
        System.out.println(throwable.getMessage());
        return "error";
    }

}
