package com.guo.springboot.netty.protocol.response;

import com.guo.springboot.netty.protocol.Packet;
import static com.guo.springboot.netty.protocol.command.Command.MESSAGE_RESPONSE;

public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
