package com.guo.springboot.netty.protocol.response;

import com.guo.springboot.netty.protocol.Packet;
import static com.guo.springboot.netty.protocol.command.Command.LOGIN_RESPONSE;

public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
