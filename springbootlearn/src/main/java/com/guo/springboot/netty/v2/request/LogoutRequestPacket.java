package com.guo.springboot.netty.v2.request;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

public class LogoutRequestPacket extends Packet {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
