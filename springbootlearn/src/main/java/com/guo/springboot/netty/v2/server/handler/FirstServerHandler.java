package com.guo.springboot.netty.v2.server.handler;

import com.guo.springboot.netty.v2.serialize.Packet;
import com.guo.springboot.netty.v2.serialize.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf)msg;
//
//        System.out.println(new Date() + "读取到客户端数据"+ byteBuf.toString(Charset.forName("utf-8")));
//
//        // 写回数据
//        ByteBuf buf = this.getByteBuf(ctx);
//
//        ctx.writeAndFlush(buf);
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        System.out.println("接收到内容:"+packet.toString());
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] buf = "hello, netty server".getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(buf);

        return byteBuf;
    }
}
