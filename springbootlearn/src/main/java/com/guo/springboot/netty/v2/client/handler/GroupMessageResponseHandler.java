package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println("收到来自【"+ msg.getSenderId() +"】群组【"+ msg.getFromGroupId() +"】消息："+ msg.getMsg());
    }
}
