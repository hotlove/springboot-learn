package com.guo.springboot.mq.rocketmq.broadcast;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BroadCastingProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_broadcasting_group");

        // 2.设置namesrv
        producer.setNamesrvAddr("47.99.145.78:9876");

        // 3.开启defaultproducer
        producer.start();

        // 4.创建消息
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message(
                    "Topic_BoradCasting_Demo", // 主题
                    "tags", // 标签 主要用于过滤消息
                    "keys", // 消息对应的唯一值 定位消息
                    ("hello12"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            messageList.add(message);
        }

        // 5.发送批量消息
        SendResult sendResult = producer.send(messageList);
        System.out.println(sendResult);

        // 6.关闭消息发送对象
        producer.shutdown();
    }
}
