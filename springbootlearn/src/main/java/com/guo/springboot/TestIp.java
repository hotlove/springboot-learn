package com.guo.springboot;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class TestIp {
    public static void main(String []args) {
        String ip = getLocalIp();
        System.out.println("localiip:"+ip);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String getLocalIp() {
        String sysEnv = "prod";
        try {
            Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
            while (faces.hasMoreElements()) { // 遍历网络接口
                NetworkInterface face = faces.nextElement();
                if (face.isLoopback() || face.isVirtual() || !face.isUp()) {
                    continue;
                }

                if (sysEnv.equals("prod") || sysEnv.equals("test") || sysEnv.equals("dev")) {
                    // 线上环境
                    // 测试环境和线上环境过滤掉其他网卡数据
                    if (!face.getDisplayName().contains("eth")) {
                        continue;
                    }
                }

                Enumeration<InetAddress> address = face.getInetAddresses();
                if (address == null) {
                    continue;
                }
                while (address.hasMoreElements()) { // 遍历网络地址
                    InetAddress addr = address.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress() && !addr.isAnyLocalAddress()) {

                        String localIp = addr.getHostAddress();
                        if (isBlank(localIp)) {
                            continue;
                        }

                        if (sysEnv.equals("prod")) {
                            // 线上环境
                            if (localIp.startsWith("172")) {
                                return localIp;
                            }
                        }

                        if (sysEnv.equals("test")) {
                            // 测试环境
                            if (localIp.startsWith("112")) {
                                return localIp;
                            }
                        }
                        return localIp;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
