package com.guo.springboot.netty.v2.request;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

import java.util.List;

public class CreateGroupRequestPacket extends Packet {

    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
