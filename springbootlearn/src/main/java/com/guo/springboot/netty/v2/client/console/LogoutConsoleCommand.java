package com.guo.springboot.netty.v2.client.console;

import com.guo.springboot.netty.v2.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        channel.writeAndFlush(new LogoutRequestPacket());
    }
}
