package com.test.memcached;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wujc
 * @ClassName MemcachedConnection
 * @Description: TODO
 * @create 2018-12-16 15:37
 */
public class MemcachedConnection implements Serializable {
    private static final long serialVersionUID = -8246150878547626154L;
    //服务器的Id
    private String ip;

    //服务器的端口
    private int port;

    //服务器的权重，默认为 1
    private int weight = 1;

    //构造服务连接对象实例
    public MemcachedConnection() {
    }

    //构造服务连接对象实例
    public MemcachedConnection(String ip, int port, int weight) {
        this.ip = ip;
        this.port = port;
        this.weight = weight;
    }

    /**
     * 将MemcachedConnection转换为XML字符串
     * @return 转换后得到的XML字符串
     */
    public String toXmlString(){
        return "<MemcachedConnection ip=\""  + ip + "\" port=\"" + port + "\" weight=\"" + weight + "\"/>";
    }

    public static MemcachedConnection valueOf(String xml) {
        String ip;
        int port;
        int weight;
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile("<MemcachedConnection.*ip=\"([0-9.]+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            ip = matcher.group(1);
        }else {
            return null;
        }

        pattern = Pattern.compile("<MemcachedConnection.*port=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            port = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }

        pattern = Pattern.compile("<MemcachedConnection.*weight=\"(\\d+?)\".*/>");
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            weight = Integer.valueOf(matcher.group(1));
        } else {
            return null;
        }
        return new MemcachedConnection(ip, port, weight);
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
