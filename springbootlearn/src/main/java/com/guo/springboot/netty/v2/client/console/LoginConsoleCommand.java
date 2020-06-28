package com.guo.springboot.netty.v2.client.console;

import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("用户名:");
        String userName = scanner.next();
        System.out.println("密码:");
        String password = scanner.next();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword(password);

        channel.writeAndFlush(loginRequestPacket);
        // 模拟登录1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
