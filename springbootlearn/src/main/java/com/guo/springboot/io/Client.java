package com.guo.springboot.io;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8081);

        // 启动发送注册信息
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String id = br.readLine();
        Message message = new Message();
        message.setFrom(Integer.parseInt(id));
        message.setType(1);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(message);

        // 开启线程循环监听发送给自己的消息
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        Message message1 = (Message) ois.readObject();
                        System.out.println("from:" + message1.getFrom() +"  content:"+message1.getMsg());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        while (true) {
            // 循环从键盘读取数据
            BufferedReader bri = new BufferedReader(new InputStreamReader(System.in));
            String msg = bri.readLine();
            String[] split = msg.split(",");

            Message sendMssage = new Message();
            sendMssage.setFrom(Integer.parseInt(id));
            sendMssage.setTo(Integer.parseInt(split[0]));
            sendMssage.setType(2);
            sendMssage.setMsg(split[1]);

            ObjectOutputStream sendoos = new ObjectOutputStream(socket.getOutputStream());
            sendoos.writeObject(sendMssage);
        }
    }
}
