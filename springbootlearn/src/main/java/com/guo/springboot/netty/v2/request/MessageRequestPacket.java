package com.guo.springboot.netty.v2.request;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/22 22:31
 * @Description:
 */
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String msg;

    public MessageRequestPacket(String toUserId, String msg) {
        this.toUserId = toUserId;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
