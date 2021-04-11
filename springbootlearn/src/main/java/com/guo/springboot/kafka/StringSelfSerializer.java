package com.guo.springboot.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Date: 2021/1/15 20:13
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class StringSelfSerializer implements Serializer<String> {
    /**
     * 自定义序列化器使用就是
     * 只需将 KafkaProducer 的 value.serializer 参数设置为 StringSerializer 类的全限定名即可
     *
     */
    private String encoding = "UTF8";
    // 配置当前类

    /**
     * configure() ⽅法，这个⽅法是在创建 KafkaProducer 实例的时候调⽤的，主要⽤来确定编码类型，
     * 不过⼀般客户端对于 key.serializer.encoding、value.serializer. encoding 和 serializer.encoding 这⼏个参 数都不会配置，
     * 在 KafkaProducer 的参数集合（ProducerConfig）⾥也没有这⼏个参数（它们可以看作⽤户⾃定义的参数），
     * 所以⼀般情况下 encoding 的值就为默认的“UTF-8”。
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey
                ? "key.serializer.encoding"
                : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("serializer.encoding");
        }
        if (encodingValue != null && encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    /**
     * 进行序列化
     * 将 data 类型转为 byte[] 类型。
     */
    @Override
    public byte[] serialize(String topic, String data) {
        try {
            if (data == null)
                return null;
            else
                return data.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error when serializing " + "string to byte[] due to unsupported encoding " + encoding);
        }
    }

    /**
     * close() ⽅法⽤来关闭当前的序列化器，⼀般情况下 close() 是⼀个空⽅法，
     * 如果实现了此⽅法，则必须确保此⽅法的幂等性，
     * 因为 这个⽅法很可能会被 KafkaProducer 调⽤多次。
     */
    @Override
    public void close() {

    }
}
