package com.test.redis.message;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wujc
 * @ClassName SubThread
 * @Description: TODO
 * @create 2018-12-02 17:02
 */
public class SubThread extends Thread {
    private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();

    private final String channel = "mychannel";

    public SubThread(JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis,channel %s,thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();//取出一个连接
            jedis.subscribe(subscriber, channel); //通过subscribe的api去订阅，入参是订阅者和频道名
        } catch (Exception e) {
            System.out.println(String.format("subscribe channel error,%s", e));
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
