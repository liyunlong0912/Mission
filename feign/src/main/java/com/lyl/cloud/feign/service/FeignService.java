package com.lyl.cloud.feign.service;

import com.lyl.cloud.feign.po.User;
import com.lyl.cloud.feign.service.impl.FeginFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * FeignService
 *
 * @author liyl
 * @date 2019-10-31
 */
@FeignClient(value = "cloud-client", fallback = FeginFallBack.class)
@Service
public interface FeignService {

    // 服务中方法的映射路径
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);


}
