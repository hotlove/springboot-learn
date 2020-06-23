package com.guo.springboot.netty.v2.codec;

import com.guo.springboot.netty.v2.serialize.Packet;
import com.guo.springboot.netty.v2.serialize.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        System.out.println("调用了编码了------------------------");
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
