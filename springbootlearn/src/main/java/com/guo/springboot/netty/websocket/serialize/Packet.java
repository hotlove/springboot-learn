package com.guo.springboot.netty.websocket.serialize;

/**
 * @Date: 2020/7/14 14:02
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public abstract class Packet {
    private Byte version;

    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
