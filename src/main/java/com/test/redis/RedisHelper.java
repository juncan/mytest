package com.test.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.regexp.internal.RE;
import com.test.setting.QueryServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wujc
 * @ClassName RedisHelper
 * @Description: redis查询器，用于Redis查询操作
 * @create 2018-11-26 20:36
 */
public class RedisHelper {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(RedisHelper.class);

    //私有构造函数（单例模式）
    private RedisHelper() {

    }

    //在borrow一个jedis实例时，是否提前进行validate操作，如果为true,则得到的jedis实例均是可用的
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    //Redis连接单例，如未开启使用redis,则返回null
    private volatile static RedisHelper _instance = null;
    public static boolean isInited = false;
    //Redis服务器配置
    RedisConnection serverList = null;

    /**
     * @MethodsName: getInstance
     * @Description: 返回Redis查询实例，如不启用Redis,返回null
     * @auther: wujc
     * @date: 2018-11-26 20:43
     * @param: []
     * @return: com.test.redis.RedisHelper
     */
    public static RedisHelper getInstance() {
        if (!QueryServiceConfig.useRedis) {
            return null;
        }
        if (_instance == null) {
            synchronized (RedisHelper.class) {
                if (_instance == null) {
                    _instance = new RedisHelper();
                    if (!isInited) {
                        RedisConnection connList = QueryServiceConfig.getInstance().getRdConnection();
                        if (!_instance.initialize(connList)) {
                            logger.error("Redis服务器连接初始化失败");
                        }else{
                            logger.info("Redis服务器连接初始化成功");
                        }
                    }
                }
            }
        }
        return _instance;
    }

    /**
     * @MethodsName: initialize
     * @Description: Redis服务器连接, 其中权重信息将被忽略
     * @auther: wujc
     * @date: 2018-11-26 20:49
     * @param: [connList]
     * @return: boolean
     */
    public boolean initialize(RedisConnection connList) {
        if (connList == null) {
            return false;
        }
        serverList = connList;
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(connList.getMax_idle());
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, connList.getIp(), connList.getPort(), connList.getTimeout(),
                    connList.getAuth(), connList.getDataBase());
            isInited = true;
            logger.info("RedisServer init success...");
            return true;
        } catch (Exception e) {
            logger.error("初始化Redis服务器失败：" + e.getMessage());
            e.printStackTrace();

        }
        return false;
    }

    public synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("获取Redis连接异常...", e);
            e.printStackTrace();
        }
        return null;
    }

    //有返回结果的回调接口定义
    public interface JedisAction<T>{
        T action(Jedis jedis);
    }

    //无返回结果的回调接口定义
    public interface JedisActionNoResult{
        void action(Jedis jedis);
    }

    /**
     * @MethodsName: execute
     * @Description: 执行有返回结果的action
     * @auther: wujc
     * @date: 2018-11-26 21:12
     * @param: [jedisAction]
     * @return: T
     */
    public <T> T execute(JedisAction<T> jedisAction) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getJedis();
            if (null != jedis) {
                return jedisAction.action(jedis);
            } else {
                logger.info("jedis is no available...");
            }
        } catch (JedisConnectionException e) {
            broken = true;
            logger.error("Redis connection lost...", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Redis Exception:", e);
        }finally {
            closeResource(jedis, broken);
        }
        return null;
    }

    public void execute(JedisActionNoResult jedisActionNoResult) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getJedis();
            if (null != jedis) {
                jedisActionNoResult.action(jedis);
            } else {
                logger.error("jedis is no available...");
            }
        } catch (JedisConnectionException e) {
            broken = true;
            logger.error("Redis connection lost:", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Redis Exception:", e);
            e.printStackTrace();
        }finally {
            closeResource(jedis, broken);
        }
    }

    /**
     * @MethodsName: closeResource
     * @Description: 根据连接是否已中断的标志，分别调用returnBrokenResource或returnResource。
     * @auther: wujc
     * @date: 2018-11-26 21:22 
     * @param: [jedis, connectionBroken]
     * @return: void
     */
    public void closeResource(Jedis jedis, boolean connectionBroken) {
        if (jedis != null) {
            try {
                if (connectionBroken) {
                    jedisPool.returnBrokenResource(jedis);
                } else {
                    jedisPool.returnResource(jedis);
                }
            } catch (Exception e) {
                logger.error("Error happen when return jedis to poll,try to close it directly.", e);
                closeRedis(jedis);
            }
        }
    }

    /**
     * @MethodsName: closeRedis
     * @Description: 光闭Redis连接
     * @auther: wujc
     * @date: 2018-11-26 21:23
     * @param: [jedis]
     * @return: void
     */
    public static void closeRedis(Jedis jedis) {
        if ((jedis != null) && jedis.isConnected()) {
            try {
                try {
                    jedis.quit();
                } catch (Exception e) {

                }
                jedis.disconnect();
            } catch (Exception e) {

            }
        }
    }

    /**
     * @MethodsName: publishMsg
     * @Description: 发布消息至Redis
     * @auther: wujc
     * @date: 2018-11-26 21:26
     * @param: [channel, message]
     * @return: boolean
     */
    public boolean publishMsg(final String channel, final String message) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                logger.info("【消息发布】向Redis发布消息：channel:%s, msg:%s", channel, message);
                jedis.publish(channel, message);
                return true;
            }
        });
    }

    /**
     * @MethodsName: getBeanList
     * @Description: 将Redis中key值对应的json格式value转换指定bean集合
     * @auther: wujc
     * @date: 2018-11-26 21:30
     * @param: [key, cls]
     * @return: java.util.List<T>
     */
    public <T> List<T> getBeanList(final String key, final Class<T> cls) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                return JSON.parseArray(jedis.get(key),cls);
            }
        });
    }

    /**
     * @MethodsName: getSingleBean
     * @Description: 将Redis中key值对应的json格式value转换成单个指定的bean
     * @auther: wujc
     * @date: 2018-11-26 21:36
     * @param: [key, cls]
     * @return: T
     */
    public <T> T getSingleBean(final String key, final Class<T> cls) {
        return execute(new JedisAction<T>() {
            @Override
            public T action(Jedis jedis) {
                return JSON.parseObject(jedis.get(key), cls);
            }
        });
    }

    /**
     * @MethodsName: getValue
     * @Description: 从reidis中获取指定key值的value,无数据则返回null
     *
     * @auther: wujc
     * @date: 2018-11-26 21:39
     * @param: [key]
     * @return: java.lang.String
     */
    public String getValue(final String key) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.get(key);
            }

        });
    }

    /**
     * @MethodsName: existValue
     * @Description: 判断key是否存在
     * @auther: wujc
     * @date: 2018-11-26 21:41
     * @param: [key]
     * @return: boolean
     */
    public boolean existValue(final String key) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    /**
     * @MethodsName: putValueByJson
     * @Description: 将json格式的数据存储到Redis
     * @auther: wujc
     * @date: 2018-11-26 21:43
     * @param: [key, value]
     * @return: void
     */
    public void putValueByJson(final String key, Object value) {
        final String jsonStr = JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect);
        putValue(key, jsonStr);
    }

    /**
     * @MethodsName: putValue
     * @Description: 设置key-value置Redis
     * @auther: wujc
     * @date: 2018-11-26 21:44
     * @param: [key, value]
     * @return: void
     */
    public void putValue(final String key, final String value) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.set(key, value);
            }
        });
    }

    /**
     * @MethodsName: delValue
     * @Description: 从Redis批量删除key
     * @auther: wujc
     * @date: 2018-11-26 21:45 
     * @param: [keys]
     * @return: boolean
     */
    public boolean delValue(final String... keys) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.del(keys) == 1 ? true : false;
            }
        });
    }

    /**
     * @MethodsName: mgetStrMap
     * @Description: 使用mget方式批量获取String键值
     * @auther: wujc
     * @date: 2018-11-26 21:47
     * @param: [keys]
     * @return: Map<String   ,   String>
     */
    public Map<String, String> mgetStrMap(final String... keys) {
        Map<String, String> result = new HashMap<String, String>();
        List<String> mget =execute(new JedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], mget.get(i));
        }
        return result;
    }

    /**
     * @MethodsName: mgetTMap
     * @Description: 使用mget方式批量获取指定类型键值
     * @auther: wujc
     * @date: 2018-11-26 21:53
     * @param: [cls, keys]
     * @return: java.util.Map<java.lang.String,T>
     */
    public <T> Map<String, T> mgetTMap(Class<T> cls, final String... keys) {
        List<String> resultList = execute(new JedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
        Map<String, T> result = new HashMap<String, T>();
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], JSON.parseObject(resultList.get(i), cls));
        }
        return result;
    }

    public <T> ArrayList<T> mgetTList(Class<T> cls, final String... keys) {
        List<String> resultList = execute(new JedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
        ArrayList<T> result = new ArrayList<T>();
        for (int i = 0; i < keys.length; i++) {
            result.add(JSON.parseObject(resultList.get(i), cls));
        }
        return result;
    }

    public void generateId(String key) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                System.out.println(jedis.incr(key));
            }
        });
    }

}


