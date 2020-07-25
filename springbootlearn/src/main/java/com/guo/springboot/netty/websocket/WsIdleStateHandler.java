package com.guo.springboot.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Date: 2020/7/13 19:59
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class WsIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 9;

    public WsIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读取到数据，关闭连接");
        ctx.channel().close();
    }
}
