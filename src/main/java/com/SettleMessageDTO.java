package com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 结算信息实体
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettleMessageDTO {

    /**
     * 结算对象Id
     *
     */
    private Long targetId;

    /**
     * 结算对象类型
     *
     */
    private String targetType;

    /**
     * 结算金额
     */
    private BigDecimal amount;

    /**
     * 结算状态
     */
    private String billStatus;
}
