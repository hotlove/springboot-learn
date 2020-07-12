package com.guo.springboot.netty.v2.response;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/29 22:24
 * @Description:
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEART_RESPONSE;
    }
}
