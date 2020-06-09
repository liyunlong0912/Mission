package com.example.demorabbitmq.controller;

import com.example.demorabbitmq.producer.MsgProducer;
import com.example.demorabbitmq.config.RabbitConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author liyl
 * @date 2020-1-3
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitConifg rabbitConifg;

    @RequestMapping(value = "/mq/{content}",method = RequestMethod.GET)
    public void testMq(@PathVariable("content") String  content) {
        MsgProducer msgProducer = new MsgProducer(rabbitConifg.rabbitTemplate());
        msgProducer.sendMsg(content);
    }

    @RequestMapping(value = "/sout",method = RequestMethod.GET)
    public void sout() {
        System.out.println("++++++++++++++++++");
    }
}
