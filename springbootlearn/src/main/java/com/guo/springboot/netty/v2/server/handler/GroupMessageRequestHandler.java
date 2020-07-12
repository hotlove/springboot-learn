package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.request.GroupMessageRequestPacket;
import com.guo.springboot.netty.v2.response.GroupMessageResponsePacket;
import com.guo.springboot.netty.v2.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        System.out.println("收到【"+ msg.getToGroupId() +"】群聊消息【"+ msg.getMessage() +"】");

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(msg.getToGroupId());

        if (channelGroup != null) {
            GroupMessageResponsePacket groupMassegeResponse = new GroupMessageResponsePacket();
            groupMassegeResponse.setFromGroupId(msg.getToGroupId());
            groupMassegeResponse.setMsg(msg.getMessage());
            groupMassegeResponse.setSenderId(SessionUtil.getSession(ctx.channel()).getUserId());
            channelGroup.writeAndFlush(groupMassegeResponse);
        }
    }
}
