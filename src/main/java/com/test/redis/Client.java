package com.test.redis;

import com.cbf4life.factory.Human;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-26 21:59
 */
public class Client {
    public static void main(String[] args) {
        RedisHelper redisHelper = RedisHelper.getInstance();
        Map<String, String> map = new HashMap<String, String>();
        map.put("福建省", "1233");
        map.put("江西省", "3323");
        map.put("武汉省", "3232");
        map.put("广西省", "4232");
        redisHelper.putValueByJson("123456",map);

        Map<String, String> mgetMap = redisHelper.mgetStrMap("123456");

    }
}
