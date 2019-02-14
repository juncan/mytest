package com.test.memcached;

import com.test.setting.QueryServiceConfig;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author wujc
 * @ClassName MemcachedQueryHelper
 * @Description: TODO
 * @create 2018-12-16 16:32
 */
public class MemcachedQueryHelper {
    private static final Logger logger = LoggerFactory.getLogger(MemcachedQueryHelper.class);

    //Memcached连接单例
    private static MemcachedQueryHelper _instance = null;

    /**
     * @MethodsName: getInstance
     * @Description: 获取Memcached查询器单例，如未开启使用Memcached，则返回null
     * @auther: wujc
     * @date: 2018-12-16 16:34
     * @return:
     */
    public static MemcachedQueryHelper getInstance() {
        if (!QueryServiceConfig.useMemcached) {
            return null;
        }
        if (_instance == null) {
            _instance = new MemcachedQueryHelper();

            MemcachedConnectionList connList = QueryServiceConfig.getInstance().getMcConnections();
            if (!_instance.initialize(connList)) {
                logger.error("Memcached服务器连接初始化失败");
            }
        }
        return _instance;
    }

    //私有构造函数（单例模式）
    private MemcachedQueryHelper() {
    }

    //Memcached客户端
    private MemcachedClient memcachedClient = null;

    //Memcached服务器列表
    MemcachedConnectionList serverList = null;

    /**
     * @MethodsName: initialize
     * @Description: 初始化Memcached服务器连接
     * @auther: wujc
     * @date: 2018-12-16 17:15
     * @param connList
     * @return:
     */
    public boolean initialize(MemcachedConnectionList connList) {
        if (connList == null || connList.size() == 0) {
            return false;
        }

        serverList = connList;

        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses(getAddress(connList)));
        try {
            memcachedClient = builder.build();
            return true;
        } catch (Exception e) {
            logger.error("初始化Memcached服务器失败：" + e.getMessage());
        }
        return false;
    }

    /**
     * @MethodsName: updateMemcachedServer
     * @Description: 更新Memcached服务器连接
     * @auther: wujc
     * @date: 2018-12-16 17:41
     * @param connList
     * @return:
     */
    public boolean updateMemcachedServer(MemcachedConnectionList connList) {
        if (connList == null) {
            return false;
        }
        try {
            //获取新增服务器列表
            MemcachedConnectionList added = new MemcachedConnectionList();
            for (int i = 0; i < connList.size(); i++) {
                //判断是否存在
                boolean exist = false;
                MemcachedConnection newAdd = connList.get(i);
                for (int j = 0; j < serverList.size(); j++) {
                    MemcachedConnection oldExist = serverList.get(j);
                    if (oldExist.getPort() == newAdd.getPort() &&
                            oldExist.getIp() == newAdd.getIp()) {
                        exist = true;
                        break;
                    }
                }
                //不存在则为新增服务器
                if (!exist) {
                    added.add(newAdd);
                }
            }
            //获取移除服务器列表
            MemcachedConnectionList removed = new MemcachedConnectionList();
            for (int i = 0; i < serverList.size(); i++) {
                //判断是否存在
                boolean exist = false;
                MemcachedConnection oldExit = serverList.get(i);
                for (int j = 0; j < connList.size(); j++) {
                    MemcachedConnection newAdd = connList.get(j);
                    if (oldExit.getPort() == newAdd.getPort() &&
                            oldExit.getIp() == newAdd.getIp()) {
                        exist = true;
                        break;
                    }
                }
                //不存在则为移除服务器
                if (!exist) {
                    removed.add(oldExit);
                }
            }

            //更新服务器列表
            serverList = connList;

            //移除服务器
            if (removed.size() > 0) {
                memcachedClient.removeServer(getAddress(removed));
            }

            //新增服务器
            if (added.size() > 0) {
                memcachedClient.addServer(getAddress(added));
            }

        } catch (Exception e) {
            logger.error("更新Memcached服务器连接异常：" + e.getMessage());
        }
        return false;
    }

    /**
     * @MethodsName: getAddress
     * @Description: 获取服务器列表字符串
     * @auther: wujc
     * @date: 2018-12-16 17:43
     * @param connList
     * @return:
     */
    public static String getAddress(MemcachedConnectionList connList) {
        String address = "";
        for (int i = 0; i < connList.size(); i++) {
            MemcachedConnection con = connList.get(i);
            address += con.getIp() + ":" + con.getPort() + " ";
        }
        return address.trim();
    }

    public Map<String, Object> getMemcachedDataInfo(List<String> keys) {
        try {
            return memcachedClient.get(keys);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setData(String key,int time,Object obj) {
        try {
            memcachedClient.set(key, time, obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
