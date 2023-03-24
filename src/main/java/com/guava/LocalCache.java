package com.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.test.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 本地缓存
 *
 * @author xingkong
 * @date 2023/3/13 10:23
 */
@Slf4j
@Component
public class LocalCache<T>  {
    private Cache<String,T> localCache = null;

    private void init(){
        localCache = CacheBuilder.newBuilder()
                //设置本地缓存容器的初始容量
                .initialCapacity(10)
                //设置本地缓存的最大容量
                .maximumSize(500)
                //设置写缓存后多少秒过期
                .expireAfterWrite(60, TimeUnit.SECONDS).build();
    }

    public void setLocalCache(String key,T object){
        localCache.put(key,object);
    }

    /***
     * 返回值 如果不存在返回null
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCache(String key){
        return (T) localCache.getIfPresent(key);
    }

    public void remove(String key){
        localCache.invalidate(key);
    }

    public static void main(String[] args) {
        LocalCache<Object> cache = new LocalCache<>();
        cache.init();

        cache.setLocalCache("1", "2");

        cache.remove("1");

        if (null != cache.getCache("1")) {
            System.out.println("缓存存在");
        }else {
            System.out.println("缓存不存在");
        }

    }


}
