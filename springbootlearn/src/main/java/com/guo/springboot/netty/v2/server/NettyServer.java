package com.guo.springboot.netty.v2.server;

import com.guo.springboot.netty.v2.codec.PacketCodecHandler;
import com.guo.springboot.netty.v2.serialize.Spliter;
import com.guo.springboot.netty.v2.server.handler.HeartBeatRequestHandler;
import com.guo.springboot.netty.v2.server.handler.IMHandler;
import com.guo.springboot.netty.v2.server.handler.IMIdleStateHandler;
import com.guo.springboot.netty.v2.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
                        nioSocketChannel.pipeline()
                                // 服务端心跳检测
                                .addLast(new IMIdleStateHandler())
                                .addLast(new Spliter())
//                                .addLast(new PackectDecoder())
                                // Netty 内部提供了一个类，叫做 MessageToMessageCodec，使用它可以让我们的编解码操作放到一个类里面去实现
                                .addLast(PacketCodecHandler.INSTANCE)
                                // ...单例模式，多个 channel 共享同一个 handler
                                .addLast(LoginRequestHandler.INSTANCE)
                                .addLast(HeartBeatRequestHandler.INSTANCE)
                                .addLast(IMHandler.INSTANCE);
//                                .addLast(new MessageRequestHandler())
//                                .addLast(new CreateGroupRequestHandler())
//                                .addLast(new GroupMessageRequestHandler())
//                                .addLast(new LogoutRequestHandler());
//                                .addLast(new PacketEncoder());
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
