package com.guo.springboot.netty.websocket;

import com.guo.springboot.netty.websocket.request.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Date: 2020/7/14 11:40
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket msg) throws Exception {
        System.out.println(msg.getMessage());
    }
}
