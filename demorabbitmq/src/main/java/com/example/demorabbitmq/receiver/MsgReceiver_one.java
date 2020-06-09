package com.example.demorabbitmq.receiver;

import com.example.demorabbitmq.config.RabbitConifg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * MsgReceiver
 *
 * @author liyl
 * @date 2020-1-2
 */
@Component
@RabbitListener(queues = {RabbitConifg.QUEUE_A})
@Slf4j
public class MsgReceiver_one {

    @RabbitHandler
    public void process(String content) {
        log.info("receiver_one接收处理队列A当中的消息： " + content);
        System.out.println("one++++++++++++++++++++++++");
    }
}
