package com.guo.springboot.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Date: 2021/1/7 15:38
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class KafkaAdminClient {
    public static void main(String[] args) {
        String brokerList = "localhost:9092";
        String topic = "topic-admin";
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);
        NewTopic newTopic = new NewTopic(topic, 4, (short) 1);
        CreateTopicsResult result = client. createTopics(Collections.singleton(newTopic));
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        client.close();
    }
}
