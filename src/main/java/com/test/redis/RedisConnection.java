package com.test.redis;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wujc
 * @ClassName RedisConnection
 * @Description: redis 服务器连接
 * @create 2018-11-26 20:11
 */
public class RedisConnection implements Serializable {
    //序列化版本号
    private static final long serialVersionUID = 4089420849702984545L;


    //Redis服务器的IP
    private String ip;

    //Redis服务器的端口
    private int port;

    //Redis服务器的访问密码，无则置为null
    private String auth;

    //Redis的当前数据库序号，默认为0
    private int dataBase = 0;

    //控制一个RedisPoll最多有多少个状态为idle（空闲的）的jedis实例，默认值也是8
    private int max_idle = 8;

    //超时设置
    private int timeout = 10000;

    //服务器权重，默认为1
    private int weight = 1;

    //构造服务器连接对象实例
    public RedisConnection() {

    }

    //构造服务器连接对象实例

    public RedisConnection(String ip, int port, String auth, int dataBase, int max_idle, int timeout, int weight) {
        this.ip = ip;
        this.port = port;
        this.auth = auth;
        this.dataBase = dataBase;
        this.max_idle = max_idle;
        this.timeout = timeout;
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getDataBase() {
        return dataBase;
    }

    public void setDataBase(int dataBase) {
        this.dataBase = dataBase;
    }

    public int getMax_idle() {
        return max_idle;
    }

    public void setMax_idle(int max_idle) {
        this.max_idle = max_idle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //将RedisConnection转换为XML字符串
    public String toXmlString() {
        return "<RedisConnection ip=\"" + ip + "\" port=\"" + port + "\" auth=\"" + auth
                + "\" max_idle=\"" + max_idle + "\" timeout=\"" + timeout + "\" dataBase=\"" + dataBase + "\" weight=\"" + weight + "\"/>";
    }

    //从XML字符串转换为RedisConnect
    public static RedisConnection valueOf(String xml) {
        String ip;
        int port;
        String auth;
        int max_idle;
        int timeout;
        int dataBase;
        int weight;
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile("<RedisConnection.*ip=\"([0-9.]+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            ip = matcher.group(1);
        } else {
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*port=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            port = Integer.valueOf(matcher.group(1));
        }else{
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*auth=\"(.*?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            auth = matcher.group(1).trim();
            if (null == auth || "".equals(auth) || "null".equalsIgnoreCase(auth)) {
                auth = null;
            }
        } else {
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*max_idle=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            max_idle = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*timeout=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            timeout = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*database=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            dataBase = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }

        pattern = Pattern.compile("<RedisConnection.*weight=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            weight = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }

        return new RedisConnection(ip, port, auth, dataBase,max_idle, timeout, weight);
    }
}
