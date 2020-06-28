package com.guo.springboot.netty.v2.client.console;


import io.netty.channel.Channel;
import io.netty.util.internal.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> commandMap = new HashMap<>();

    public ConsoleCommandManager() {
        commandMap.put(ConsoleCommand.LOGIN_COMMAND, new LoginConsoleCommand());
        commandMap.put(ConsoleCommand.LOGOUT_COMMAND, new LogoutConsoleCommand());
        commandMap.put(ConsoleCommand.SEND_MSG_USER, new SendToUserConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入命令:");
        String command = scanner.next();
        if (!StringUtil.isNullOrEmpty(command) && (command.trim().length() > 0)) {
            ConsoleCommand consoleCommand = commandMap.get(command);
            consoleCommand.exec(scanner, channel);
        } else {
            // 如果输入 空命令则重新进行输入
            System.out.println("无法识别指令["+command+"] 请重新输入");
        }
    }
}
