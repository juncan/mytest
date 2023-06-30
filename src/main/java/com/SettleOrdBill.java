package com;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/5/5 11:30
 */
@Data
public class SettleOrdBill {
    private String sn;

    private String type;

    private String useType;

    private String orderSn;

    private String settleTarget;

    private String paymentMethod;

    private LocalDateTime paymentTime;

    private String registerId;

    private String taskType;

    private String acctName;

    private String acctNo;

    private String bankNo;

    private String bankName;

    private String companyNo;

    private BigDecimal settlePrice;

    private BigDecimal confirmPrice;

    private BigDecimal billPrice;

    private String billStatus;

    private String settleStatus;

    private String stType;

    private Long storeId;

    private String storeName;

    private Long merchantId;

    private String merchantName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String taskSn;

    private String fileSn;

    private String adjustReason;

    private String deferReason;

    private String appId;

    private String failReason;

    private LocalDateTime rejectTime;

    private LocalDateTime manualPayTime;

    private String isPrepayment;

    //===================站点涉及结算信息=================
    private Long siteId;
    private String siteName;
    private Long siteAppId;
    private String siteAppName;
    private String siteType;
    private BigDecimal serviceAmount;
}
