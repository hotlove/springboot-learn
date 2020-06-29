package com.guo.springboot.netty.v2.command;

public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;

    Byte CREATE_GROUP_REQUEST = 6;

    Byte CREATE_GROUP_RESPONSE = 7;

    Byte GROUP_MESSAGE_REQUEST = 8;

    Byte GROUP_MESSAGE_RESPONSE = 9;

    Byte HEART_BEART_REQUEST = 10;

    Byte HEART_BEART_RESPONSE = 11;
}
