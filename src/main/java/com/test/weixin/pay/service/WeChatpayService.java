package com.test.weixin.pay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wujc
 * @ClassName WeChatpayService
 * @Description: TODO
 * @create 2018-11-12 14:11
 */
public interface WeChatpayService {
    /**
     * 创建支付订单
     *
     * @param orderId   订单号
     * @param price     订单价格
     * @param body      主题信息
     * @param ipAddress 地址
     * @return
     * @throws IOException
     */
    Map createOrder(String orderId, BigDecimal price, String body, String ipAddress) throws IOException;

    /**
     * 微信服务器调用该请求，进行数据异步传回作用
     * @param request
     * @param response
     * @return 一个代表接受成功/失败的xml信息（失败原因应该是：签名失败，成功则表示收到数据，微信不需要在发送数据到服务器）
     * @throws Exception
     */
    String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
