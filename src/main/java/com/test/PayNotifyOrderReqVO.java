package com.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * 支付单通知请求信息
 * @author xingkong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyOrderReqVO {
    private String merchantOrderId;


    private Long payOrderId;

    private String channelCode;

    boolean flag;

    private Map<String,String> extra;
}
