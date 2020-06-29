package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/29 22:08
 * @Description: 心跳包
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    // 发送心跳包的间隔时间
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        scheduleSendHeartBeat(ctx);

        super.channelInactive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        // 每隔指定时间发送心跳包
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
