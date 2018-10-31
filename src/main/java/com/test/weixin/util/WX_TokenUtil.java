package com.test.weixin.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.test.common.cache.Cache;
import com.test.common.cache.CacheManager;
import com.test.weixin.config.WXLoginConfig;
import com.test.weixin.constant.WeiXinEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:
 * WX_TokenUtil
 *
 * @author administrator
 * @create 2018-07-01 15:51
 */
public class WX_TokenUtil {
    private static Logger log = LoggerFactory.getLogger(WX_TokenUtil.class);
    /**
     *  获得微信 AccessToken
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     * 开发者需要access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取
     * 的access_token失效。
     * （此处我是把token存在Redis里面了）
     */
    public static String getWXToken() {
        Cache cache = CacheManager.getCacheInfo(WeiXinEnum.CACHE_TYPE.CACHE_WX_ACCESS_TOKEN.name());
        String access_token = "";
        if (cache != null) {
            access_token = (String) cache.getValue();
            return access_token;

        }else{
            WXLoginConfig wx=new WXLoginConfig();
            String appId = wx.appid;
            String appSecret = wx.appSecret;
            String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId+"&secret="+ appSecret;
            JSONObject jsonObject = WX_HttpsUtil.httpsRequest(tokenUrl, "GET", null);
            if (null != jsonObject) {
                try {
                    access_token = jsonObject.getString("access_token");
                    Cache cacheNew = new Cache();
                    cacheNew.setKey(WeiXinEnum.CACHE_TYPE.CACHE_WX_ACCESS_TOKEN.name());
                    cacheNew.setTimeOut(Integer.valueOf(jsonObject.getString("expires_in")));
                    cacheNew.setValue(access_token);
                    CacheManager.putCache(WeiXinEnum.CACHE_TYPE.CACHE_WX_ACCESS_TOKEN.name(), cacheNew);
                    return access_token;
                } catch (JSONException e) {
                    access_token = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
                }
            }
        }
        return null;
    }
}
