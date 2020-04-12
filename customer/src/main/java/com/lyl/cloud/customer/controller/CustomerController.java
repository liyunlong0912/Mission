package com.lyl.cloud.customer.controller;

import com.lyl.cloud.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyl
 * @date 2019-10-18
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return customerService.hiService(name);
    }


    @RequestMapping(value = "/hit")
    public void hit() {
        System.out.println("+++++++++++++++++");
    }
}


