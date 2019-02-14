package com.test.memcached;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-01-24 16:56
 */
public class Client {
    public static void main(String[] args) {
        MemcachedQueryHelper helper = MemcachedQueryHelper.getInstance();

        helper.setData("kkk", 1000, "hello");
        helper.setData("eee", 1000, "world");
        helper.setData("rrr", 1000, 3323);

        List<String> keys = new ArrayList<>();
        keys.add("kkk");
        keys.add("eee");
        keys.add("rrr");
        Map<String, Object> map= helper.getMemcachedDataInfo(keys);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
        }

    }
}
