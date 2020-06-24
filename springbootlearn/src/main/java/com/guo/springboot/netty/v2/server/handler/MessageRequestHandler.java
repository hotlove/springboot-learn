package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.request.MessageRequestPacket;
import com.guo.springboot.netty.v2.response.MessageResponsePacket;
import com.guo.springboot.netty.v2.util.Session;
import com.guo.springboot.netty.v2.util.SessionUtil;
import io.netty.channel.Channel;
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

        // 获取session信息
        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        System.out.println("【"+session.getUserId()
                +"】发送消息给【"+messageRequestPacket.getToUserId()+"】消息内容：  "+messageRequestPacket.getMsg()+"");

        // 目标人员id
        String toUserId = messageRequestPacket.getToUserId();
        // 目标channel
        Channel toChannel = SessionUtil.getChanel(toUserId);

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        if (SessionUtil.hasLogin(toChannel)) {
            // 如果对方在线
            messageResponsePacket.setMsg(messageRequestPacket.getMsg());
        } else {
            // 如果对方不在线
            messageResponsePacket.setMsg("对方不在线");
        }

        toChannel.writeAndFlush(messageResponsePacket);

    }
}
