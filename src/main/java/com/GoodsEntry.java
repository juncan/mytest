package com;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author xingkong
 * @date 2022/10/17 18:08
 */
@Data
public class GoodsEntry {

    private BigDecimal entryPrice;

    private LocalDateTime entryTime;
}
