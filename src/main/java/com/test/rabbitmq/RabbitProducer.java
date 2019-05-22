package com.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wujc
 * @ClassName RabbitProducer
 * @Description: 提供者
 * @create 2019-05-08
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAMWE = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.209.20";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("test");
        factory.setPassword("test");

        Connection connection = factory.newConnection();//创建连接
        Channel channel = connection.createChannel(); //创建信道
        //创建一个type="direct"、持久化的、非自动删除的交换机
        channel.exchangeDeclare(EXCHANGE_NAMWE, "direct", true, false, null);
        //创建一个持久的、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换机与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAMWE, ROUTING_KEY);
        //发送一条持久化的信息
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAMWE, ROUTING_KEY,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }
}
