package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/22 22:28
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println("收到来自【"+ messageResponsePacket.getFromUserId()+"】的消息:  "+messageResponsePacket.getMsg());
    }
}
