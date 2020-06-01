package com.guo.springboot.io;

import java.io.*;
import java.net.Socket;

public class MsgThread implements Runnable{

    private Socket socket;

    private Integer from;

    public MsgThread(Integer from, Socket socket) {
        this.from = from;
        this.socket = socket;
    }


    @Override
    public void run() {
        while (true) {
            try {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Message message = (Message) objectInputStream.readObject();

                Socket socket = LoginTable.getInstance().getSocket(message.getTo());
                if (socket != null) {
                    // 说明发送目标存在
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(message);
                    System.out.println("from:"+message.getFrom()+"向 to:"+message.getTo()+"发送消息:"+message.getMsg());
//                    oos.flush();
//                    oos.close();
                } else {
                    // 写回数据 目标对象未登录
                    OutputStream outputStream = this.socket.getOutputStream();
                    Message backMsg = new Message();
                    backMsg.setMsg("目标对象未登录");
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(backMsg);
//                    oos.flush();
//                    oos.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
