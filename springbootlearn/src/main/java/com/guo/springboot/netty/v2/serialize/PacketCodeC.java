package com.guo.springboot.netty.v2.serialize;

import com.guo.springboot.netty.v2.command.Command;
import com.guo.springboot.netty.v2.request.LoginRequestPacket;
import com.guo.springboot.netty.v2.request.MessageRequestPacket;
import com.guo.springboot.netty.v2.response.LoginResponsePacket;
import com.guo.springboot.netty.v2.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x123456; // int = 4byte

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public final Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();

    public final Map<Byte, Serializer> serializerMap = new HashMap<>();

    public PacketCodeC() {
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap.put(SerializerAlogrithm.KYRO, new KyroSerializer());
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        KyroSerializer kyroSerializer = new KyroSerializer();
        byte[] bytes = kyroSerializer.serialize(packet);

        // 1byte 魔数| 1byte 协议版本号 | 1byte 序列化算法 | 1byte 协议指令 | 4byte 内容长度 | nbyte 实际内容
        byteBuf.writeInt(MAGIC_NUMBER); // 4byte 魔数
        byteBuf.writeByte(packet.getVersion()); // 1byte 协议版本号
        byteBuf.writeByte(kyroSerializer.getSerializerAlogrithm()); // 1byte 序列化算法
        byteBuf.writeByte(packet.getCommand()); // 1 byte 指令
        byteBuf.writeInt(bytes.length); // 4byte 内容长度
        byteBuf.writeBytes(bytes); // nbyte 内容
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4); // 跳过魔数
        byteBuf.skipBytes(1); // 跳过 协议版本号

        byte serializeAlogrithm = byteBuf.readByte(); // 序列化算法
        byte command = byteBuf.readByte(); // 指令
        int contentLength = byteBuf.readInt(); // 内容长度
        byte[] content = new byte[contentLength];
        byteBuf.readBytes(content);// 内容

        Serializer serializer = this.getSerializer(serializeAlogrithm);
        Class<? extends Packet> packetClazz = this.getCommand(command);

        if (serializer != null && packetClazz != null) {
            Packet deserialize = serializer.deserialize(packetClazz, content);
            return deserialize;
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlogrithm) {
        return serializerMap.get(serializeAlogrithm);
    }

    private Class<? extends Packet> getCommand(byte command) {
        return packetMap.get(command);
    }

}
