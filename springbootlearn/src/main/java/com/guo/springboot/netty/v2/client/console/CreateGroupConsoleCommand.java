package com.guo.springboot.netty.v2.client.console;

import com.guo.springboot.netty.v2.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入群聊人员id 以都好隔开：");
        String userIds = scanner.next();

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIds(userIds);

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
