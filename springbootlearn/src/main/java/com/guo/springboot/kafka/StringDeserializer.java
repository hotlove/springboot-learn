package com.guo.springboot.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Date: 2021/1/15 20:32
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class StringDeserializer implements Deserializer<String> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public String deserialize(String topic, byte[] data) {
        try {
            return new String(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
