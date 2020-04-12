package com.lyl.cloud.feign.service.impl;

import com.lyl.cloud.feign.po.User;
import com.lyl.cloud.feign.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * @author liyl
 * @date 2019-10-31
 */
@Component
public class FeginFallBack implements FeignService {

    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("dd", 3);
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
