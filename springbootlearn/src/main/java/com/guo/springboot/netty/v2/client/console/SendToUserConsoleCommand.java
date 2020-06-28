package com.guo.springboot.netty.v2.client.console;

import com.guo.springboot.netty.v2.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入发送消息:");
        String userId = scanner.next();
        String message = scanner.next();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket(userId, message);
        channel.writeAndFlush(messageRequestPacket);
    }
}
