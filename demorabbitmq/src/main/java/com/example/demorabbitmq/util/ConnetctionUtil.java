package com.example.demorabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ConnectionUtil
 *
 * @author liyl
 * @date 2019-12-20
 */
public class ConnetctionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {

        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("liyl");
        factory.setPassword("123456");
        factory.setVirtualHost("testhost");

        // 获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
