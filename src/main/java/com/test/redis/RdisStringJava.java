package com.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author wujc
 * @ClassName RdisStringJava
 * @Description: TODO
 * @create 2018-07-15 10:05
 */
public class RdisStringJava {
    public static void main(String[] args) {
        //连接本地的Redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        jedis.set("runoobkey", "www.ruunoob.com");
        //获取存储的数据并输出
        System.out.println("redis存储的字符串为："+jedis.get("runoobkey"));
    }
}
