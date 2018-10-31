package com.test.weixin.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:
 * 微信用户工具类
 *
 * @author wujc
 * @create 2018-07-01 15:44
 */
public class WX_UserUtil {
    private static final Logger log = LoggerFactory.getLogger(WX_UserUtil.class);

    public static Integer subscribeState(String openId) {
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+WX_TokenUtil.getWXToken() +"&openid="+openId;
        JSONObject result = WX_HttpsUtil.httpsRequest(tmpurl, "GET",null);
        JSONObject resultJson = new JSONObject(result);
        log.error("获取用户是否订阅errcode:{} errmsg{}", resultJson.getInteger("errcode"), resultJson.getString("errmsg"));
        String errmsg = (String) resultJson.get("errmsg");
        if (errmsg == null) {
            //用户是否订阅了该公众号标识（0代表此用户没有关注该公众号 1代表关注了该公众号）
            Integer subscribe = (Integer) resultJson.get("subscribe");
            return subscribe;
        }
        return -1;
    }
}
