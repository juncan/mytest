package com.test.setting;

import com.test.memcached.MemcachedConnectionList;
import com.test.redis.RedisConnection;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;

/**
 * @author wujc
 * @ClassName QueryServiceConfig
 * @Description: 服务配置消息读取
 * @create 2018-11-26 17:28
 */
public class QueryServiceConfig {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(QueryServiceConfig.class);

    //获取日志
    public Logger getLogger() {
        return logger;
    }

    //配置文件路径
    private static String configFile = "QueryService.config";

    //配置文件路径
    private URI uriConfigFile = null;

    //JMX服务IP地址
    private String jmxHost;

    //JMX服务端口
    private int jmxPort;

    //Memcached服务器是否启用（默认关闭）
    public static boolean useMemcached = true;
    //Memcached服务器连接配置版本号
    private int mcConnectionVersion;
    //Memcached服务器连接配置
    private MemcachedConnectionList mcConnections;

    //Rdis服务是否启用（默认开启）
    public static boolean useRedis = true;
    //Redis服务器连接配置版本号
    private int rdConnectionVersion;
    //Redis服务器连接配置
    private RedisConnection rdConnection;

    //系统配置单例
    private static QueryServiceConfig _instance = null;

    public static QueryServiceConfig getInstance() {
        if (_instance == null) {
            _instance = new QueryServiceConfig();
            try {
                _instance.uriConfigFile = getResourceUri(configFile);
                _instance.loadConfig();
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return _instance;
    }

    private QueryServiceConfig() {
    }

    /**
     * @MethodsName: getResourceUri
     * @Description: 获取指定文件名的URI
     * @auther: wujc
     * @date: 2018-11-26 19:51
     * @param: [name:文件名称]
     * @return: java.net.URI
     */
    public static URI getResourceUri(String name) throws Exception {
        ClassLoader classLoader = getTCL();

        URL url = classLoader.getResource(name);

        return url == null ? null : url.toURI();
    }


    private static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {
        Method method = null;
        try {
            method = Thread.class.getMethod("getContextClassLoader", (Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            return null;
        }
        return (ClassLoader) method.invoke(Thread.currentThread(), (Object[]) null);
    }

    /**
     * @MethodsName: loadConfig
     * @Description: 加载配置问价
     * @auther: wujc
     * @date: 2018-11-26 19:56
     * @param: []
     * @return: boolean
     */
    public boolean loadConfig() {
        try {
            File file = new File(uriConfigFile);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);

            readSections(document.getRootElement());

            return true;
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @MethodsName: readSections
     * @Description: 读取各个配置节
     * @auther: wujc
     * @date: 2018-11-26 19:59
     * @param: [root]
     * @return: void
     */
    public void readSections(Element root) {
        Node node;

        //Memcached服务器连接配置
        node = root.selectSingleNode("MemcachedSection");
        if (node != null) {
            readMemcachedConfig(node);
        }
        //Redis服务器连接配置
        node = root.selectSingleNode("RedisSection");
        if (node != null) {
            readRedisConfig(node);
        }
    }

    /**
     * @param node
     * @MethodsName: readMemcachedConfig
     * @Description: 读取Memcached服务器连接配置
     * @auther: wujc
     * @date: 2018-12-16 16:56
     * @return:
     */
    private void readMemcachedConfig(Node node) {
        //配置版本号
        Node temp = node.selectSingleNode("Version");
        if (temp != null) {
            mcConnectionVersion = Integer.parseInt(temp.getText());
        }

        //是否启用开关
        temp = node.selectSingleNode("Switch");
        if (temp != null) {
            useMemcached = Boolean.parseBoolean(temp.getText());
        }
        //网络连接配置
        temp = node.selectSingleNode("MemcachedConnectionList");
        if (temp != null) {
            mcConnections = MemcachedConnectionList.valueOf(temp.asXML());
        }
    }

    /**
     * @MethodsName: readRedisConfig
     * @Description: 读取redis服务器连接配置
     * @auther: wujc
     * @date: 2018-11-26 20:03
     * @param: [node]
     * @return: void
     */
    private void readRedisConfig(Node node) {
        //配置版本号
        Node temp = node.selectSingleNode("Version");
        if (temp != null) {
            rdConnectionVersion = Integer.parseInt(temp.getText());
        }

        //是否启用开关
        temp = node.selectSingleNode("Switch");
        if (temp != null) {
            useRedis = Boolean.parseBoolean(temp.getText());
        }

        //网络连接配置
        temp = node.selectSingleNode("RedisConnection");
        if (temp != null) {
            rdConnection = RedisConnection.valueOf(temp.asXML());
        }
    }

    //获取Redis服务器连接配置
    public RedisConnection getRdConnection() {
        return rdConnection;
    }

    public MemcachedConnectionList getMcConnections() {
        return mcConnections;
    }
}
