package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("你被拉入群聊【"+ createGroupResponsePacket.getUserNames()
                +"】 群组id【"+createGroupResponsePacket.getGroupId()+"】");
    }
}
