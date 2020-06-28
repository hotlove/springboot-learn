package com.guo.springboot.netty.v2.util;

import com.guo.springboot.netty.v2.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/6/23 22:17
 * @Description:
 */
public class SessionUtil {

    private static final Map<String, Channel> sessionMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    /**
     * 绑定登录用户和channel
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel) {
        sessionMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 取消用户session和channel的绑定
     * @param channel
     */
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            sessionMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /**
     * 绑定群组
     * @param groupId
     * @param channels
     */
    public static void bindChannelGroup(String groupId, ChannelGroup channels) {
        channelGroupMap.put(groupId, channels);
    }

    /**
     * 取消绑定群组
     * @param groupId
     */
    public static void unbindChannelGroup(String groupId) {
        if (channelGroupMap.containsKey(groupId)) {
            channelGroupMap.remove(groupId);
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
