package com.guo.springboot.netty.v2.client.handler;

import com.guo.springboot.netty.v2.serialize.LoginPacket;
import com.guo.springboot.netty.v2.serialize.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(new Date() + "读取到服务端数据"+ byteBuf.toString(Charset.forName("utf-8")));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端写出数据");

//        // 获取数据
//        ByteBuf byteBuf = this.getByteBuf(ctx);

        // 写数据
//        ctx.channel().writeAndFlush(byteBuf);

        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUserId(1);
        loginPacket.setUserName("xiaoqiang");
        loginPacket.setPassword("123");

        ByteBuf byteBuf = ctx.alloc().buffer();

        PacketCodeC.INSTANCE.encode(byteBuf, loginPacket);
        System.out.println(loginPacket);
        System.out.println(byteBuf.toString());

        ctx.channel().writeAndFlush(byteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] buf = "hello, netty server".getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(buf);

        return byteBuf;
    }
}
