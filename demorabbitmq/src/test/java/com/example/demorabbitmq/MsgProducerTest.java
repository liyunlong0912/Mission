package com.example.demorabbitmq;

import com.example.demorabbitmq.config.RabbitConifg;
import com.example.demorabbitmq.producer.MsgProducer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MsgProducerTest {

    @Autowired
    private RabbitConifg rabbitConifg;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void test() {
        // MsgProducer msgProducer = new MsgProducer(rabbitConifg.rabbitTemplate());
        // msgProducer.sendMsg("aaaaa");
        // msgProducer.sendMsg("bbbbb");
        // msgProducer.sendMsg("ccccc");
        rabbitTemplate.convertAndSend(RabbitConifg.EXCHANGE_A, RabbitConifg.ROUTINGKEY_A, "AAAA");
        rabbitTemplate.convertAndSend(RabbitConifg.EXCHANGE_A, RabbitConifg.ROUTINGKEY_A, "BBBB");
        rabbitTemplate.convertAndSend(RabbitConifg.EXCHANGE_A, RabbitConifg.ROUTINGKEY_A, "CCCC");

    }

    @Test
    public void sendAll() {
        MsgProducer msgProducer = new MsgProducer(rabbitConifg.rabbitTemplate());
        msgProducer.SendAll("sendADD");
    }
}