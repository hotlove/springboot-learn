package com.guo.springboot.netty.v2.client;

import com.guo.springboot.netty.v2.client.handler.LoginResponseHanlder;
import com.guo.springboot.netty.v2.client.handler.MessageResponseHandler;
import com.guo.springboot.netty.v2.codec.PackectDecoder;
import com.guo.springboot.netty.v2.codec.PacketEncoder;
import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import com.guo.springboot.netty.v2.request.MessageRequestPacket;
import com.guo.springboot.netty.v2.serialize.Spliter;
import com.guo.springboot.netty.v2.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Scanner;

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
                Channel channel = ((ChannelFuture) future).channel();
                // 开启异步线程获取键盘输入值
                startConsoleThread(channel);
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });

    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(() -> {
            while (!Thread.interrupted()) {

                if (!LoginUtil.hasLogin(channel)) {
                    // 如果未登录则进行登录
                    System.out.println("用户名:");
                    String userName = sc.next();
                    System.out.println("密码:");
                    String password = sc.next();

                    loginRequestPacket.setUserName(userName);
                    loginRequestPacket.setPassword(password);

                    channel.writeAndFlush(loginRequestPacket);
                    // 模拟登录1s
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("输入消息:");
                    String toUserId = sc.next();
                    String message = sc.next();

                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }
}
