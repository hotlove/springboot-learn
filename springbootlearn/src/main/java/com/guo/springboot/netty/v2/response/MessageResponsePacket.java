package com.guo.springboot.netty.v2.response;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/22 22:28
 * @Description:
 */
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
