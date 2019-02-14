package com.test.aliyun;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-09 16:12
 */
public class Client {
    public static void main(String[] args) {
        SmsReqDto smsReqDto = new SmsReqDto();
        smsReqDto.setArea_code("0086");
        smsReqDto.setPhone("");
        smsReqDto.setJson_data("{\"code\":\"" + "您好，您的手机已欠费，请及时充值" + "\"}");
        smsReqDto.setTemplate_code("");
        try {
            SendSms.send(smsReqDto);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
