package com.guo.springboot.netty.websocket.request;

import com.guo.springboot.netty.websocket.command.Command;
import com.guo.springboot.netty.websocket.serialize.Packet;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Date: 2020/7/14 11:39
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MessagePacket extends Packet {

    private String fromId;

    private String message;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
