package com.push.xiaomi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujc
 * @ClassName Constant
 * @Description: 模板定义
 * @create 2018-11-01 9:39
 */
public class Constant {
    public static Map<Integer,String> iosSecretKey() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "/eoGIkDt/Z+X2g1kAsL9Zg==");
        map.put(2, "hd2r3QZ0CUDjUsMW5B3T7w==");
        return map;
    }
    public static Map<Integer,String> androidSeretKey() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "CvsvT5RHPzWeWAsv3GNKmQ==");
        map.put(2, "o3R9nqVqp9K41feIbnmg9A==");
        return map;
    }

    public static Map<Integer,String> packageName() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "");
        map.put(2, "");
        return map;
    }
}
