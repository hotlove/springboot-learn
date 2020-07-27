package com.guo.springboot.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws MQClientException {

//        startConsumer2();
        startConsumer1();
    }

    public static void startConsumer1() throws MQClientException {
        // 1.创建defaultmqpushconsumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo_consumer_group");

        // 2.设置namesrv地址
        consumer.setNamesrvAddr("47.99.145.78:9876");

        // 设置消息拉取最大数
        consumer.setConsumeMessageBatchMaxSize(2);

        // 3.设置subscribe,这里是要读取的主题信息
        consumer.subscribe(
                "Topic_Demo",  // 制定消费主题
                "*"// 过滤规则 比如Tags || TagA || TagB
        );

        // 4.创建消息监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("开始消费");
                // 5.消费消息
                for (MessageExt messageExt : list) {
                    // 获取主题
                    String topic = messageExt.getTopic();
                    // 获取标签
                    String tags = messageExt.getTags();
                    // 获取信息
                    byte[] body = messageExt.getBody();
                    try {
                        String result = new String(body, RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("consumer消费信息---"+topic+",tags:"+tags+",result:"+result);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        // 消息重试
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                // 6.返回状态
                // 消费消息完成
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 7。开启
        consumer.start();
        System.out.printf("Consumer Started111.%n");
    }

    public static void startConsumer2() throws MQClientException {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("47.99.145.78:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("Topic_Demo", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    // 获取主题
                    String topic = msg.getTopic();
                    // 获取标签
                    String tags = msg.getTags();
                    // 获取信息
                    byte[] body = msg.getBody();
                    try {
                        String result = new String(body, RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("consumer消费信息---"+topic+",tags:"+tags+",result:"+result);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        // 消息重试
//                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
