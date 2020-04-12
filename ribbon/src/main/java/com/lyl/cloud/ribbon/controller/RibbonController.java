package com.lyl.cloud.ribbon.controller;

import com.lyl.cloud.ribbon.service.RibbonService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author liyl
 * @date 2019-10-21
 */
@RestController
public class RibbonController {

    @Autowired
    /**
     *  注入负载均衡客户端
     */
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RibbonService ribbonService;


    @RequestMapping("/consumer")
    public String helloConsumer() {

        //这里是根据配置文件的那个providers属性取的
        ServiceInstance providers = loadBalancerClient.choose("providers");
        URI uri = URI.create(String.format("http://%s:%s", providers.getHost(), providers.getPort()));
        return uri.toString();
    }

    @RequestMapping("/hello")
    public String hello() {
        return restTemplate.getForEntity("http://CLOUD-CLIENT/hello", String.class).getBody();
    }

    @RequestMapping("/helloservice")
    public String helloService() throws Exception {
        return ribbonService.helloService();
    }

    @RequestMapping("/hiservice")
    public Observable<String> hiservice() throws Exception {
        return ribbonService.hiService();
    }

    @RequestMapping("/service")
    public String service() throws Exception {
        HelloServiceCommand command = new HelloServiceCommand("hello", restTemplate);
        String execute = command.execute();
        return execute;
    }

    @RequestMapping("/get")
    public String get() throws Exception {
        List list = new ArrayList<>();
        HelloServiceObserveCommand command = new HelloServiceObserveCommand("hello", restTemplate);
        // 热执行
        Observable<String> observe = command.observe();
        // 冷执行
        // Observable<String> stringObservable = command.toObservable();
        Thread.sleep(5000);
        // 订阅
        observe.subscribe(new Observer<String>() {

            // 请求完成的方法
            @Override
            public void onCompleted() {
                System.out.println("汇聚完了所有请求！");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            // 订阅调用事件，结果汇聚的地方，用集合去装返回的结果汇聚起来
            @Override
            public void onNext(String s) {
                System.out.println("结果出来了！");
                list.add(s);
            }
        });
        return list.toString();
    }

    @RequestMapping("/getcache")
    public String getCache() throws ExecutionException, InterruptedException {
        System.out.println("访问来了！");
        // Hystrix的缓存实现，这功能有点鸡肋。
        HystrixRequestContext.initializeContext();
        HelloServiceCommand command = new HelloServiceCommand("hello", restTemplate);
        String execute = command.execute();
        //清理缓存
        // HystrixRequestCache.getInstance("hello").clear();
        return "hello cloud";
    }


}
