package com.guo.springboot.netty.v2.client;

import com.guo.springboot.netty.v2.client.handler.LoginResponseHanlder;
import com.guo.springboot.netty.v2.client.handler.MessageResponseHandler;
import com.guo.springboot.netty.v2.codec.PackectDecoder;
import com.guo.springboot.netty.v2.codec.PacketEncoder;
import com.guo.springboot.netty.v2.serialize.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
//                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4)) // Integer.MAX_VALUE内容最大长度, 7是协议前面那些固定长度的字节数，4是存储内容长度的字节数
                                .addLast(new Spliter())
                                .addLast(new PackectDecoder())
                                .addLast(new LoginResponseHanlder())
                                .addLast(new MessageResponseHandler())
                                .addLast(new PacketEncoder());
                    }
                });

        bootstrap.connect("localhost", 8087).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });

    }
}
