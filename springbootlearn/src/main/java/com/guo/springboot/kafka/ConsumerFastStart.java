package com.guo.springboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Date: 2020/12/28 16:51
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ConsumerFastStart {

    public static final String brokerList = "192.168.20.52:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializezr");
        properties.put("bootstrap.servers", brokerList);
        //设置消费组的名称，具体的释义可以参⻅第3章
        properties.put("group.id", groupId);
        //创建⼀个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties, new StringDeserializer(), new StringDeserializer());
        //订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        //循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }
}
