package com.test.redis.message;

import redis.clients.jedis.JedisPubSub;

/**
 * @author wujc
 * @ClassName Subscriber
 * @Description: TODO
 * @create 2018-12-02 16:58
 */
public class Subscriber extends JedisPubSub {
    public Subscriber() {
    }

    @Override
    public void onMessage(String channel, String message) { //收到消息会调用
        System.out.println(String.format("receive redis published message,channel %s,message %s", channel, message));
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) { //订阅了频道会调用
        System.out.println(String.format("subscribe redis  channel success,channel %s,subscribedChannels %d",
                channel, subscribedChannels));
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) { //取消订阅会调用
        System.out.println(String.format("unsubscribe redis channel,channel %s,subscribedChannels %d",
                channel, subscribedChannels));
    }
}
