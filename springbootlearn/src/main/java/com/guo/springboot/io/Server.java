package com.guo.springboot.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8081);

        System.out.println("服务器开始在8081端口监听........");
        while (true) {
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Message message = (Message) objectInputStream.readObject();
            if (message.getType().equals(1)) {
                // 登录信息
                handLogin(message, socket);
            }
        }
    }

    public static void handLogin(Message message, Socket socket) {
        System.out.println("登录用户："+message.getFrom());
        LoginTable.getInstance().putLoginInfo(message.getFrom(), socket);

        MsgThread msgThread = new MsgThread(message.getFrom(), socket);
        new Thread(msgThread).start(); // 启动新线程管理连接信息；
    }
}
