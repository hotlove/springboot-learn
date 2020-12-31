package com.guo.springboot.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);
        // KafkaProducer 是线程安全的，可以在多个线程中共享单个 KafkaProducer 实例，也可以将 KafkaProducer 实例进⾏池化来供其他线程调⽤。
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties, new StringSerializer(), new StringSerializer());
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, Kafka!");
        try {
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
