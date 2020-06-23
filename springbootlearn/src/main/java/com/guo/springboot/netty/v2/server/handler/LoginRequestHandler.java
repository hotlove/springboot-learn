package com.guo.springboot.netty.v2.server.handler;


import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import com.guo.springboot.netty.v2.util.Session;
import com.guo.springboot.netty.v2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {

        // 服务端开始处理登录请求 并分配userid
        String userId = UUID.randomUUID().toString().substring(0, 5);

        System.out.println("["+msg.getUserName()+"] 登录服务器, 分配用户id["+userId+"]");

        // 创建用户session数据
        Session session = new Session();
        session.setUserId(userId);
        session.setUserName(msg.getUserName());
        // 将channel与用户进行绑定
        SessionUtil.bindSession(session, ctx.channel());

        // 回执服务器消息给客户端
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setMessage("恭喜您登录服务器成功, 用户id为["+userId+"]");

        ctx.channel().writeAndFlush(loginResponsePacket);
    }
}
