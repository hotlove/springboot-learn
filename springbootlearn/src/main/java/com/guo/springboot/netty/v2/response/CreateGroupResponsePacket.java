package com.guo.springboot.netty.v2.response;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.serialize.Packet;

public class CreateGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    private String userNames;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
