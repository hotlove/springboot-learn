package com.guo.springboot.netty.websocket.serialize;

import com.guo.springboot.netty.websocket.command.Command;
import com.guo.springboot.netty.websocket.request.MessagePacket;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2020/7/14 17:29
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class PacketCodec {

    private Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();

    public PacketCodec() {
        packetMap.put(Command.MESSAGE_REQUEST, MessagePacket.class);
    }

//    public TextWebSocketFrame decode(Packet packet) {
//
//    }

}
