package com.guo.springboot.io;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class LoginTable {

    private ConcurrentHashMap<Integer, Socket> loginTable = new ConcurrentHashMap<>();

    private static class Holder {
        private final static LoginTable holder = new LoginTable();
    }

    public static LoginTable getInstance() {
        return Holder.holder;
    }

    public void putLoginInfo(Integer id, Socket socket) {
        this.loginTable.putIfAbsent(id, socket);
    }

    public Socket getSocket(Integer id) {
        return this.loginTable.get(id);
    }

    public void deleteSocket(Integer id) {
        this.loginTable.remove(id);
    }
}
