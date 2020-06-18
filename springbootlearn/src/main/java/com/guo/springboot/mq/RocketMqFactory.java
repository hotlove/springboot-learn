package com.guo.springboot.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.ConcurrentHashMap;

public class RocketMqFactory {

    private String namesrvAddr = "127.0.0.1:9876";

    private ConcurrentHashMap<String, DefaultMQProducer> producerMap = new ConcurrentHashMap<>();

    private static class Holder {
        private static final RocketMqFactory instance = new RocketMqFactory();
    }

    private RocketMqFactory() {}

    public static RocketMqFactory getInstance() {
        return Holder.instance;
    }

    public DefaultMQProducer getProducer(String groupName) {
        DefaultMQProducer producer = producerMap.get(groupName);

        if (producer == null) {

            producer = new DefaultMQProducer(groupName);
            producer.setNamesrvAddr(namesrvAddr);
            try {
                producer.start();
                producerMap.put(groupName, producer);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }

        return producer;
    }

    public SendResult sendMsg(String msg, String topic) {
        SendResult sendResult = null;
        try {
            Message message = new Message(topic, "notice", msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            sendResult = RocketMqFactory.getInstance().getProducer("testGroup").send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendResult;
    }

    public static void main(String[] args) {
        String msg = "tes111t";
        String topic = "mytopic";

        SendResult sendResult = RocketMqFactory.getInstance().sendMsg(msg, topic);
        System.out.printf("%s%n", sendResult);
    }

}
