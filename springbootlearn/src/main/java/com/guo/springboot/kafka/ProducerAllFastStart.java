package com.guo.springboot.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Date: 2021/1/15 21:49
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ProducerAllFastStart {
    public static final String brokerList = "192.168.20.52:9092,192.168.20.52:9093,192.168.20.52:9094";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties props = new Properties();
        // kafka服务器地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        // key序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 自定义拦截器
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());
        // 自定义value序列化器
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSelfSerializer.class.getName());
        // 自定义分区器
//        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DemoPartitioner.class.getName());

        // kafka生产者客户端
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

        // 消息记录
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello,workdxxx");

        try {
            kafkaProducer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        kafkaProducer.close();
    }
}
