package com.test.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述:
 * 缓存管理类
 *
 * @author wujc
 * @create 2018-07-06 17:40
 */
public class CacheManager {
    private static HashMap cacheMap = new HashMap();

    //单实例构造方法
    private CacheManager() {
        super();
    }

    //获取布尔值的缓存
    public static boolean getSimpleFlag(String key) {
        try {
            return (Boolean) cacheMap.get(key);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static long getServerStartdt(String key) {
        try {
            return (Long) cacheMap.get(key);
        } catch (Exception e) {
            return 0;
        }
    }

    //设置布尔值缓存
    public synchronized static boolean setSimpleFlag(String key, boolean flag) {
        if (flag && getSimpleFlag(key)) { //假如为真不允许被覆盖
            return false;
        }else {
            cacheMap.put(key, flag);
            return true;
        }
    }

    public synchronized static boolean setSimpleFlag(String key, long severbegrundt) {
        if (cacheMap.get(key) == null) {
            cacheMap.put(key, severbegrundt);
            return true;
        } else {
            return false;
        }
    }

    //得到缓存。同步静态方法
    private synchronized static Cache getCache(String key) {
        return (Cache) cacheMap.get(key);
    }

    //判断是否存在一个缓存
    private synchronized static boolean hasCache(String key) {
        return cacheMap.containsKey(key);
    }

    //清除缓存
    public synchronized static void clearAll() {
        cacheMap.clear();
    }

    //清除某一类特定缓存，通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
    public synchronized static void clearAll(String type) {
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        ArrayList arr = new ArrayList();
        try {
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.startsWith(type)) { //如果匹配则删除掉
                    arr.add(key);
                }
            }
            for (int k = 0; k < arr.size(); k++) {
                clearOnly((String) arr.get(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //清除指定的缓存
    private static void clearOnly(String key) {
        cacheMap.remove(key);
    }

    //载入缓存
    public synchronized static void putCache(String key, Cache obj) {
        cacheMap.put(key, obj);
    }

    //获取缓存信息
    public static Cache getCacheInfo(String key) {
        if (hasCache(key)) {
            Cache cache = getCache(key);
            if (cacheExpired(cache)) { //调用判断是否终止方法
                cache.setExpired(true);
            }
            return cache;
        } else {
            return null;
        }
    }

    //载入缓存信息
    public static void putCacheInfo(String key, Cache obj, long dt, boolean expired) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setTimeOut(dt + System.currentTimeMillis()); //设置多久更新缓存
        cache.setValue(obj);
        cache.setExpired(expired); //缓存默认载入时，终止状态为False
        cacheMap.put(key, cache);

    }

    //重写载入缓存信息方法
    public static void pushCacheInfo(String key, Cache obj, long dt) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setTimeOut(dt + System.currentTimeMillis());
        cache.setValue(obj);
        cache.setExpired(false);
        cacheMap.put(key, cache);
    }

    //判断缓存是否终止
    public static boolean cacheExpired(Cache cache) {
        if (null == cache) { //传入的缓存不存在
            return false;
        }
        long nowDt = System.currentTimeMillis();//系统当前的毫秒数
        long cacheDt = cache.getTimeOut(); //缓存内的过期毫秒数
        if (cacheDt <= 0 || cacheDt > nowDt) { //过期时间小于等于零时，或者过期时间大于当前时间时，则为FALSE
            return false;
        }else{ //大于过期时间 即过期
            return true;
        }
    }

    //获取缓存中的大小
    public static int getCacheSize() {
        return cacheMap.size();
    }

    //获取指定的类型的大小
    public static int getCacheSize(String type) {
        int k = 0;
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        try {
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) { //如果匹配则删除掉
                    k++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    //获取婚车对象中的所有键值名称
    public static ArrayList getCacheAllKey() {
        ArrayList a = new ArrayList();
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                a.add((String) entry.getKey());
            }
        } catch (Exception cacheAllKey) {

        }finally {
            return a;
        }
    }

    //获取缓存对象中指定类型的键值名称
    public static ArrayList getCacheListKey(String type) {
        ArrayList a = new ArrayList();
        String key;
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) {
                    a.add(key);
                }
            }
        } catch (Exception e) {

        }finally {
            return a;
        }
    }


}
