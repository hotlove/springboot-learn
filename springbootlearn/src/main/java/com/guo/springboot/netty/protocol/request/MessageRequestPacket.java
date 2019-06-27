package com.guo.springboot.netty.protocol.request;

import com.guo.springboot.netty.protocol.Packet;
import static com.guo.springboot.netty.protocol.command.Command.*;

public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
