package com.guo.springboot.netty.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Date: 2020/7/14 14:14
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class PacketCodecHanlder extends MessageToMessageCodec {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("code in");
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("code out");
    }
}
