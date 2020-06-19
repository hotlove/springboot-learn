package com.guo.springboot.netty.v2.serialize;

public abstract class Packet {

    private Byte version = 1;

    abstract Byte getCommand();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }
}
