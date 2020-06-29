package com.guo.springboot.netty.v2.client.console;

import com.guo.springboot.netty.v2.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群组id及消息:");
        String groupId = scanner.next();
        String message = scanner.next();

        GroupMessageRequestPacket groupMessage = new GroupMessageRequestPacket();
        groupMessage.setToGroupId(groupId);
        groupMessage.setMessage(message);

        channel.writeAndFlush(groupMessage);
    }
}
