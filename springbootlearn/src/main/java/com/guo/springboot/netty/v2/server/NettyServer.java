package com.guo.springboot.netty.v2.server;

import com.guo.springboot.netty.v2.server.handler.FirstServerHandler;
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
                        nioSocketChannel.pipeline().addLast(new FirstServerHandler());
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
