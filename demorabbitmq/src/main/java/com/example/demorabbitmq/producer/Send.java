package com.example.demorabbitmq.producer;

import com.example.demorabbitmq.util.ConnetctionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Send 生产者
 *
 * @author liyl
 * @date 2019-12-23
 */
public class Send {

    private static final String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {

        // 获取连以及mq通道
        Connection connection = ConnetctionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 消息内容
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("[x]Send '" + message + "'");
        // 关闭通道、连接
        channel.close();
        connection.close();

    }


}
