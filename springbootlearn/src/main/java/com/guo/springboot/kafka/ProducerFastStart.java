package com.guo.springboot.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Date: 2020/12/28 16:48
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ProducerFastStart {
    public static final String brokerList = "192.168.20.52:9092,192.168.20.52:9093,192.168.20.52:9094";
//    public static final String brokerList = "192.168.20.52:9092";
    public static final String topic = "topic-demo";
    public static void main(String[] args) {
        Properties properties = new Properties();
        // 这种方式不好容易写错推荐下面那种写法
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 自定义序列化器
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, com.guo.springboot.kafka.StringSerializer.class.getName());
        // 推荐下面哪种方式编写
//        properties.put("bootstrap.servers", brokerList);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        // KafkaProducer 是线程安全的，可以在多个线程中共享单个 KafkaProducer 实例，也可以将 KafkaProducer 实例进⾏池化来供其他线程调⽤。
        // 这里key和value序列可以不放在properties中配置，放在构造方法里也是可以的
//        KafkaProducer<String, String> producer = new KafkaProducer<>(properties, new StringSerializer(), new StringSerializer());
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, Kafka!");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
