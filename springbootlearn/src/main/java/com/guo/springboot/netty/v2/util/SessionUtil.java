package com.guo.springboot.netty.v2.util;

import com.guo.springboot.netty.v2.attribute.Attributes;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/23 22:17
 * @Description:
 */
public class SessionUtil {

    private static final Map<String, Channel> sessionMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        sessionMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            sessionMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChanel(String userId) {
        return sessionMap.get(userId);
    }
}
