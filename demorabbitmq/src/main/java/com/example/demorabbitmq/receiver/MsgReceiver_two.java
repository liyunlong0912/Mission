package com.example.demorabbitmq.receiver;

import com.example.demorabbitmq.config.RabbitConifg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * MsgReceiver_two
 *
 * @author liyl
 * @date 2020-1-3
 */
@Component
@RabbitListener(queues = {RabbitConifg.QUEUE_A})
@Slf4j
public class MsgReceiver_two {

    @RabbitHandler
    public void process(String content) {
        log.info("receiver_two接受消息队列A中的消息" + content);
        System.out.println("two++++++++++++++++++++++++");
    }
}
