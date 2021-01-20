package com.guo.springboot.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/7/28 21:04
 * @Description: 顺序消息生产者
 */
public class OrderProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 1. 创建DefaultMQProducer
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("demo_producer_group");

        // 2.设置namesrv
        defaultMQProducer.setNamesrvAddr("47.99.145.78:9876");

        // 3.开启defaultproducer
        defaultMQProducer.start();

        // 4.创建消息

        // 5.发送消息
        // 第一个参数发送内容，第二个参数选中指定得消息队列对象（会将所有消息队列传入进来），第三个参数指定对应得队列下表
        for (int i = 0; i < 5; i++) {
            Message message = new Message(
                    "Topic_Order_Demo", // 主题
                    "tags", // 标签 主要用于过滤消息
                    "keys", // 消息对应的唯一值 定位消息
                    ("hello"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送5次
            SendResult sendResult = defaultMQProducer.send(
                    message,
                    new MessageQueueSelector() {
                        @Override
                        public MessageQueue select(List<MessageQueue> list, Message message, Object args) {// 这里得args和下面得arg是对应起来得
                            // 获取队列下标
                            Integer index  = (Integer)args;
                            // 获取对应得下标得队列
                            return list.get(index);
                        }
                    },
                    1
            );
            System.out.println(sendResult);
        }

        // 6.关闭消息发送对象
        defaultMQProducer.shutdown();
    }
}
