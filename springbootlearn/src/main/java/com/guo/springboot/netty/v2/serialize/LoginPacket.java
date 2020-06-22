package com.guo.springboot.netty.v2.serialize;

import com.guo.springboot.netty.v2.command.Command;

public class LoginPacket extends Packet {

    private Integer userId;

    private String userName;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String toString() {
        return "userId:"+userId+" userName:"+userName+" password:"+password;
    }
}
