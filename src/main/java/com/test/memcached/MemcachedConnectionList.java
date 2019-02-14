package com.test.memcached;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wujc
 * @ClassName MemcachedConnectionList
 * @Description: Memcached服务器连接列表，用于设置个系统与Memcached服务器集群的连接
 * @create 2018-12-16 16:44
 */
public class MemcachedConnectionList implements Serializable {

    private static final long serialVersionUID = 6737835012213360041L;

    //Memcached服务器连接
    private List<MemcachedConnection> connections = new LinkedList<MemcachedConnection>();

    /**
     * @MethodsName: add
     * @Description: 添加Memcached服务器连接
     * @auther: wujc
     * @date: 2018-12-16 16:46
     * @param connection
     * @return:
     */
    public void add(MemcachedConnection connection) {
        this.connections.add(connection);
    }

    /**
     * @MethodsName: size
     * @Description: 获取Memcached服务器的连接列表大小
     * @auther: wujc
     * @date: 2018-12-16 16:47
     * @return:
     */
    public int size() {
        return connections.size();
    }

    /**
     * @MethodsName: get
     * @Description: 获取指定位置上的Memcached服务器连接
     * @auther: wujc
     * @date: 2018-12-16 16:48
     * @param index
     * @return:
     */
    public MemcachedConnection get(int index) {
        return connections.get(index);
    }

    /**
     * @MethodsName: valueOf
     * @Description: 从XML字符串转换为MemcachedConnectionList
     * @auther: wujc
     * @date: 2018-12-16 16:51
     * @param xml
     * @return:
     */
    public static MemcachedConnectionList valueOf(String xml) {
        Pattern pattern = Pattern.compile("<MemcachedConnection(.+?)/>");
        Matcher matcher = pattern.matcher(xml);

        MemcachedConnectionList connList = new MemcachedConnectionList();

        while (matcher.find()) {
            String connStr = "<MemcachedConnection" + matcher.group(1) + "/>";
            MemcachedConnection connection = MemcachedConnection.valueOf(connStr);
            if (connection != null) {
                connList.add(connection);
            }
        }
        return connList;
    }

    /**
     * @MethodsName: toXmlString
     * @Description: 将MemcachedConnectionList转换为XML字符串
     * @auther: wujc
     * @date: 2018-12-16 16:54
     * @return:
     */
    public String toXmlString() {
        StringBuffer temp = new StringBuffer();
        temp.append("<MemcachedConnectionList>" + "\r\n");
        for (int i = 0; i < connections.size(); i++) {
            temp.append(connections.get(i).toXmlString() + "\r\n");
        }
        temp.append("</MemcachedConnectionList>");
        return temp.toString();
    }
}
