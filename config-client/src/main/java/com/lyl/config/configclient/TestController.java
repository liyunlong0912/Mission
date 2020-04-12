package com.lyl.config.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author liyl
 * @date 2019-11-13
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;

    @RequestMapping("/test")
    public String test() {
        return this.name + this.age;
    }
}
