package com.guo.springboot.mq.rocketmq.transation;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/7/28 22:57
 * @Description:
 */
public class TxProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {
        // 1. 创建DefaultMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("demo_producer_tx_group");
        // 2.设置namesrv
        producer.setNamesrvAddr("47.99.145.78:9876");

        // 3.指定消息监听对象，用于执行本地事务和消息回查
        // 存储对应事务得状态信息  key:事务id, value: 当前事务执行状态
        ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
        TransactionListener transactionListener = new TransactionListener(){
            // 执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                // 事务id
                String transactionId = message.getTransactionId();

                // 0状态位置 1，本地事务执行成功 2.本地事务执行失败
                localTrans.put(transactionId, 0);

                // 业务执行， 处理本地事务，service
                System.out.println("hello demo local tx executed!!!");

                try {
                    System.out.println("正在执行本地事务，一般会调用dao");
                    Thread.sleep(12000);
                    System.out.println("正在执行本地事务, sucess");
                    localTrans.put(transactionId, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    localTrans.put(transactionId, 2);
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }

                return LocalTransactionState.COMMIT_MESSAGE;
            }

            // 消息回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                // 获取对应事务状态信息
                String transactionId = messageExt.getTransactionId();
                // 获取状态
                Integer status = localTrans.get(transactionId);

                switch (status) {
                    case 0:
                        return LocalTransactionState.UNKNOW;
                    case 1:
                        return LocalTransactionState.COMMIT_MESSAGE;
                    case 2:
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.UNKNOW;
            }
        };
        producer.setTransactionListener(transactionListener);

        // 4.配置线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-tx-msg-check-thread");
                        return thread;
                    }
                }
        );
        producer.setExecutorService(executorService);


        // 5.开启defaultproducer
        producer.start();

        // 6.创建消息
        Message message = new Message(
                "Topic_Tx_Demo", // 主题
                "tags", // 标签 主要用于过滤消息
                "keys-tx", // 消息对应的唯一值 定位消息
                "hello12".getBytes(RemotingHelper.DEFAULT_CHARSET)
        );

        // 7.发送事务消息
        TransactionSendResult sendResult = producer.sendMessageInTransaction(message, "hello-tx");
        System.out.println(sendResult);

        // 8.关闭消息发送对象
        producer.shutdown();
    }
}
