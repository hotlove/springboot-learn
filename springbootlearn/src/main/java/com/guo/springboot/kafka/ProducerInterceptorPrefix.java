package com.guo.springboot.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Date: 2021/1/15 21:41
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {
    /**
     * 实现⾃定义的 ProducerInterceptorPrefix 之后，需要在 KafkaProducer 的配置参数 interceptor.classes 中指定这个拦截器，
     * 此参数的默认值为“”。
     * ⽰例如下： properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());
     *
     * KafkaProducer 中不仅可以指定⼀个拦截器，还可以指定多个拦截器以形成拦截链。
     * 拦截链会按照 interceptor.classes 参数配置的拦截器的顺序来⼀⼀执⾏（配置的时候，各个拦截器之间使⽤逗号隔开）
     */
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;


    /**
     * KafkaProducer 在将消息序列化和计算分区之前会调⽤⽣产者拦截器的 onSend() ⽅法来对消息进⾏相应的定制化操作。
     * ⼀般来说最好不要修改消息 ProducerRecord 的 topic、key 和 partition 等信息，如果要 修改，则需确保对其有准确的判断，
     * 否则会与预想的效果出现偏差。⽐如修改 key 不仅会影响分区的计算，同样会影响 broker 端⽇志压缩（Log Compaction）的功能。
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifyName = "prefix1-" + record.value();
        return new ProducerRecord<>(
                record.topic(),
                record.partition(),
                record.timestamp(),
                record.key(),
                modifyName,
                record.headers());
    }

    /**
     * KafkaProducer 会在消息被应答（Acknowledgement）之前或消息发送失败时调⽤⽣产者拦截器的 onAcknowledgement() ⽅法，
     * 优先于⽤户设定的 Callback 之前执⾏。这个⽅法运⾏在 Producer 的I/O线程 中，
     * 所以这个⽅法中实现的代码逻辑越简单越好，否则会影响消息的发送速度
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    /**
     * ⽤于在关闭拦截器时执⾏⼀些资源的清理⼯作
     */
    @Override
    public void close() {
        double successRatio = (double)sendSuccess / (sendFailure + sendSuccess);
        System.out.println("[INFO] 发送成功率=" + String.format("%f", successRatio * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
