package com.guo.springboot.netty.v2.server;

import com.guo.springboot.netty.v2.codec.PackectDecoder;
import com.guo.springboot.netty.v2.codec.PacketEncoder;
import com.guo.springboot.netty.v2.serialize.Spliter;
import com.guo.springboot.netty.v2.server.handler.FirstServerHandler;
import com.guo.springboot.netty.v2.server.handler.LoginRequestHandler;
import com.guo.springboot.netty.v2.server.handler.LogoutRequestHandler;
import com.guo.springboot.netty.v2.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//                        nioSocketChannel.pipeline().addLast(new FirstServerHandler());
                        nioSocketChannel.pipeline()
//                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4)) // Integer.MAX_VALUE内容最大长度, 7是协议前面那些固定长度的字节数，4是存储内容长度的字节数
                                .addLast(new Spliter())
                                .addLast(new PackectDecoder())
                                .addLast(new LoginRequestHandler())
                                .addLast(new MessageRequestHandler())
                                .addLast(new LogoutRequestHandler())
                                .addLast(new PacketEncoder());
                    }
                });

        bind(serverBootstrap, 8087);

    }

    public static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("服务器启动成功 端口["+port+"]");
                }  else {
                    System.out.println("端口绑定["+port+"]失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });

//        serverBootstrap.bind(port).addListener(future -> {
//            if (future.isSuccess()) {
//                System.out.println("服务器启动成功 端口["+port+"]");
//            }  else {
//                System.out.println("端口绑定["+port+"]失败");
//                bind(serverBootstrap, port + 1);
//            }
//        });
    }
}
