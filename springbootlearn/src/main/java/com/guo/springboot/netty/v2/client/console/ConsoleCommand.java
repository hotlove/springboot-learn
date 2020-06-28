package com.guo.springboot.netty.v2.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    String LOGIN_COMMAND = "loginCommand"; // 登录命令

    String LOGOUT_COMMAND = "logout"; // 登出命令

    String SEND_MSG_USER = "sendUser"; // 发送消息给个人

    String SEND_MSG_GROUP = "sendGroup"; // 发送消息给群组

    void exec(Scanner scanner, Channel channel);
}
