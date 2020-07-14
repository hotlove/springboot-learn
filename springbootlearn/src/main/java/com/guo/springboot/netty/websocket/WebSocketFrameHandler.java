package com.guo.springboot.netty.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.Locale;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/7/11 23:55
 * @Description:
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final String HEART_BEAT = "HeartBeat";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {

        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame)frame;
            String content = textWebSocketFrame.text();
            System.out.println("收到来自客户端消息:"+ content);

            if (HEART_BEAT.equals(content)) {
                // 心跳检测包 直接写会
                ctx.channel().writeAndFlush(new TextWebSocketFrame(HEART_BEAT));
            } else {
                ctx.channel().writeAndFlush(new TextWebSocketFrame("123"));
            }

        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            System.out.println(message);
            throw new UnsupportedOperationException(message);
        }
    }

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channle，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        super.handlerAdded(ctx);
        System.out.println("客户端建立连接");
        ctx.channel().writeAndFlush("连接完成");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        System.out.println("客户端断开，channle对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channle对应的短id为：" + ctx.channel().id().asShortText());
    }
}
