package com.guo.springboot.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class RoecketMqFactory {

    private static final String namesrvAddr = "47.99.145.78:9876";

    private static class Holder {
        private static final RoecketMqFactory instance = new RoecketMqFactory();
    }

    public static final RoecketMqFactory getInstance() {
        return Holder.instance;
    }

    public DefaultMQProducer getProducer(String groupName) {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);

        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        return producer;
    }

}
