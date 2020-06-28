package com.guo.springboot.netty.v2.request;

import com.guo.springboot.netty.v2.serialize.Packet;

import java.util.List;

public class CreateGroupRequestPacket extends Packet {

    private List<String> userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public Byte getCommand() {
        return null;
    }
}
