package com.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xingkong
 * @Title: DateRange
 * @Description: 时间范围
 * @date 2021/12/23 17:48
 */
@Data
public class DateRange implements Serializable {
    private static final long serialVersionUID = -1999375247342390212L;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
}
