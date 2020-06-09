package com.example.demorabbitmq;

import com.example.demorabbitmq.receiver.MyConsumer;
import com.example.demorabbitmq.util.ConnetctionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class DemorabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemorabbitmqApplication.class, args);
    }

    /**
     * Receiver 消费者
     *
     * @author liyl
     * @date 2019-12-23
     */
    public static class Receiver {

        private static final String QUENE_NAME="q_test_01";

        public static void main(String[] args) throws IOException, TimeoutException {
            // 获取连接
            Connection connection = ConnetctionUtil.getConnection();
            // 从连接中创建通道
            Channel channel = connection.createChannel();
            // 声明通道
            channel.queueDeclare(QUENE_NAME,false,false,false,null);
            // 同一时刻服务器只会发送一条消息给消费者
            channel.basicQos(1);
            channel.basicConsume(QUENE_NAME, true, new MyConsumer(channel));
        }
    }
}
