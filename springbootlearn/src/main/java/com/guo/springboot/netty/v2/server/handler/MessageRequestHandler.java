package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.request.MessageRequestPacket;
import com.guo.springboot.netty.v2.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/22 22:34
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println("来自客户端的消息:"+messageRequestPacket.getMsg());

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMsg("服务端消息：发生大事");
        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
