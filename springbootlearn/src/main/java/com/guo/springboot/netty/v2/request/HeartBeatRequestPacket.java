package com.guo.springboot.netty.v2.request;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/29 22:10
 * @Description:
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEART_REQUEST;
    }
}
