package com.test.weixin.pay.service.impl;

import com.test.weixin.config.WXLoginConfig;
import com.test.weixin.pay.WxUtils;
import com.test.weixin.pay.service.WeChatpayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wujc
 * @ClassName WeChatPayServiceImpl
 * @Description: TODO
 * @create 2018-11-12 14:20
 */
public class WeChatPayServiceImpl implements WeChatpayService {
    private String placeUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    WxUtils wxUtils = new WxUtils();
    WXLoginConfig config = new WXLoginConfig();
    @Override
    public Map createOrder(String orderId, BigDecimal price, String body, String ipAddress) throws IOException {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", config.appid);
        parameters.put("mch_id", config.mchId);
        parameters.put("device_info", "WEB");
        parameters.put("body", body);
        parameters.put("nonce_str", wxUtils.gen32RandomString());
        parameters.put("notify_url", config.notifyUrl);
        parameters.put("out_trade_no", orderId);
        //parameters.put("total_fee", price.multiply(BigDecimal.valueOf(100)).intValue());
        parameters.put("total_fee", 1); //测试将支付金额设置成1分钱
        parameters.put("spbill_create_ip", ipAddress);
        parameters.put("trade_type", "App");
        parameters.put("sign", wxUtils.createSign(parameters, config.key)); //sign必须在最后
        String result = wxUtils.executeHttpPost(placeUrl, parameters);//执行Http请求，获取接收的字符串（一段xml）
        return wxUtils.createSign2(result, config.key);
    }

    @Override
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //预先设定返回的response的类型为xml
        response.setHeader("Content-type", "application/xml");
        //读取参数，解析xml为map
        Map<String, String> map = wxUtils.transferXmlToMap(wxUtils.readRequest(request));
        //转换为有序map,判断签名是否正确
        boolean isSignSuccess = wxUtils.checkSign(new TreeMap<String, Object>(map), config.key);
        if (isSignSuccess) {
            //签名校验成功，说明是微信服务器发出的数据
            String orderId = map.get("out_trade_no");

        }
        return null;
    }
}
