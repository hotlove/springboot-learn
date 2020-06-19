package com.guo.springboot.netty.v2.serialize;

public class Hessian2Serializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.HESSIAN2;
    }

    @Override
    public byte[] serialize(Object object) {

        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
