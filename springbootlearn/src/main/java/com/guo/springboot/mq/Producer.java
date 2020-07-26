package com.guo.springboot.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建DefaultMQProducer
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("demo_producer_group");

        // 2.设置namesrv
        defaultMQProducer.setNamesrvAddr("47.99.145.78:9876");

        // 3.开启defaultproducer
        defaultMQProducer.start();

        // 4.创建消息
        Message message = new Message(
                "Topic_Demo", // 主题
                "tags", // 标签 主要用于过滤消息
                "keys", // 消息对应的唯一值 定位消息
                "hello".getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // 5.发送消息
        SendResult sendResult = defaultMQProducer.send(message);
        System.out.println(sendResult);

        // 6.关闭消息发送对象
        defaultMQProducer.shutdown();
    }
}
