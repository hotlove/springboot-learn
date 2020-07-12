package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.request.CreateGroupRequestPacket;
import com.guo.springboot.netty.v2.response.CreateGroupResponsePacket;
import com.guo.springboot.netty.v2.util.Session;
import com.guo.springboot.netty.v2.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        String groupId = UUID.randomUUID().toString().substring(0, 5);
        // channel 组
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        String userIdsStr = createGroupRequestPacket.getUserIds();
        List<String> userIds = Arrays.asList(userIdsStr.split(","));
        List<String> userNames = new ArrayList<>(userIds.size());

        // 循环遍历找到登录用户channel 并加入组中
        for (String userId : userIds) {
            Channel chanel = SessionUtil.getChanel(userId);
            if (chanel != null) {
                channelGroup.add(chanel);
                Session session = SessionUtil.getSession(chanel);
                userNames.add(session.getUserName());
            }
        }

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNames(String.join(",", userNames));
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("创建群聊【"+ groupId +"】");
        System.out.println("里面有【"+ String.join(",", userNames) +"】");
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
