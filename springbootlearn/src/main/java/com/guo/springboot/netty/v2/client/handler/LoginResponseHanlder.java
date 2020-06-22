package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.attribute.Attributes;

import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class LoginResponseHanlder extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassword("pwd");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        Boolean success = msg.getSuccess();
        if (success) {
            ctx.channel().attr(Attributes.LOGIN).set(true);
            System.out.println("登录成功:"+success);
        } else {
            System.out.println("登录失败:"+msg.getMessage());
        }
    }

}
