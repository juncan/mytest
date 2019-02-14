package com.test.weixin.pay;

import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.mp.bean.result.WxMpPrepayIdResult;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wujc
 * @ClassName WxPayInfoResp
 * @Description: TODO
 * @create 2018-11-09 14:35
 */
public class WxPayInfoResp {
    private String app_id;
    private String partner_id;
    private String prepay_id;
    private String wx_package;
    private String noncestr;
    private String timestamp;
    private String sign;

    public WxPayInfoResp() {
    }

    public WxPayInfoResp(WxMpPrepayIdResult result, String partnerKey) {
        this.app_id = result.getAppid();
        this.partner_id = result.getMch_id();
        this.prepay_id = result.getPrepay_id();
        this.wx_package = "Sign=WXPay";
        this.noncestr = System.currentTimeMillis() + "";
        this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        //生成签名用的map
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("appid", this.app_id);
        map.put("partnerid", this.partner_id);
        map.put("prepayid", this.prepay_id);
        map.put("package", this.wx_package);
        map.put("noncestr", this.noncestr);
        map.put("timestamp", this.timestamp);
        String sign = WxCryptUtil.createSign(map, partnerKey);
        this.sign = sign;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getWx_package() {
        return wx_package;
    }

    public void setWx_package(String wx_package) {
        this.wx_package = wx_package;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
