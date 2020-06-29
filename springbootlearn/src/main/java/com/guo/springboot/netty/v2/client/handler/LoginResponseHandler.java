package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import com.guo.springboot.netty.v2.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        Boolean success = msg.getSuccess();
        if (success) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(msg.getMessage());
        } else {
            System.out.println("登录失败");
        }
    }

}
