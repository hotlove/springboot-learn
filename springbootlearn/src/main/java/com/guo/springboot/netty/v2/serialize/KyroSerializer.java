package com.guo.springboot.netty.v2.serialize;

public class KyroSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.KYRO;
    }

    @Override
    public byte[] serialize(Object object) {
        return KyroUtil.writeObject(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return KyroUtil.readObject(bytes, clazz);
    }
}
