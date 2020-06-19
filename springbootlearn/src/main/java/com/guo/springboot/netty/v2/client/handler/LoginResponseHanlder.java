package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.attribute.Attributes;
import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHanlder extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        Boolean success = msg.getSuccess();
        if (success) {
            ctx.channel().attr(Attributes.LOGIN).set(true);
        } else {
            System.out.println("登录失败:"+msg.getMessage());
        }
    }
}
