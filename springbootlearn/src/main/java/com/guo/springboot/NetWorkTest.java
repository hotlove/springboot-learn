package com.guo.springboot;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Date: 2021/1/13 11:11
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class NetWorkTest {
    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("112.29.174.223", 9999));
//        OutputStream out = socket.getOutputStream();
//        byte[] bytes= new byte[100 * 1024];
//        out.write(bytes);
//        System.in.read();
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateStr = "20161112131415";
        LocalDateTime yyyymmddhhmiss = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS"));
        System.out.println(yyyymmddhhmiss);
    }
}
