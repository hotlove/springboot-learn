package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.request.LogoutRequestPacket;
import com.guo.springboot.netty.v2.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {

        if (msg.getCommand().equals(Command.LOGOUT_REQUEST)) {
            // 下线
            System.out.println("用户【"+SessionUtil.getSession(ctx.channel()).getUserId()+"】已下线");

            SessionUtil.unBindSession(ctx.channel());
            // 关闭通道
            ctx.channel().close();
        }
    }
}
