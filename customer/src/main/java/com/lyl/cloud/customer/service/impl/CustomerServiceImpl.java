package com.lyl.cloud.customer.service.impl;

import com.lyl.cloud.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liyl
 * @date 2019-10-21
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String hiService(String name) {
        return restTemplate.getForObject("http://CLOUD-CLIENT/hi?name=" + name, String.class);
    }
}
