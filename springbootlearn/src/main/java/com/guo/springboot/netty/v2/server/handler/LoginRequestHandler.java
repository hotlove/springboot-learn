package com.guo.springboot.netty.v2.server.handler;


import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {

        System.out.println("收到消息[用户名:"+msg.getUserName()+" 密码:" + msg.getPassword());
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setMessage("登录成功");

        ctx.channel().writeAndFlush(loginResponsePacket);
    }
}
