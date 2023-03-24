package com.enums;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 邮储对账对象 psbc_settle_detail
 *
 * @author cwb
 * @date 2022-12-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class PsbcSettleDetail{
    private static final long serialVersionUID = 1L;

    private String payDate;

    private String payType;

    private String timeStamp;

    private LocalDateTime payTime;

    private String payOrderId;

    private String psbcNo;

    private BigDecimal payAmount;

    private BigDecimal otherAmount;

}
