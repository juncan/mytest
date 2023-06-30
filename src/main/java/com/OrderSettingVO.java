package com;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单配置信息
 *
 * @author xingkong
 * @date 2022/3/22 17:13
 */
@Data
public class OrderSettingVO implements Serializable {
    private static final long serialVersionUID = -5243247771580614985L;

    private Integer autoCancel;

    private Integer expireUnRefundTimes;

    private Boolean sendExpireSmsSwitch;

    private Boolean sendPayOrderSmsSwitch;

    private Integer transitionDay;

    private Integer advanceBookingTime;

    private Integer autoRefund;

    private Integer canOrderNum;
}
