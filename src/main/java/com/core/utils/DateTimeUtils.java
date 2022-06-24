package com.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2022/6/24 21:36
 */
public class DateTimeUtils {

    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final String DATE_FORM = "yyyy-MM";


    //1、 ==================1、获取当天，当月最早时间和最晚时间 ========================

    /**
     * 获取当天的开始时间
     * 示例:2020-08-21T00:00
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取当天的结束时间
     * 示例:2020-08-21T23:59:59.999999999
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }


    /**
     * 获取一个月的开始时间
     * 示例: 2020-08-01T00:00
     */
    public static LocalDateTime getMonthStart(LocalDateTime time) {
        return time.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取一个月的结束时间
     * 示例: 2020-08-31T23:59:59.999999999
     */
    public static LocalDateTime getMonthDayEnd(LocalDateTime time) {
        return time.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
    }


    /**
     * 获取当天的开始时间
     * 示例:2020-08-21T00:00
     */
    public static LocalDateTime getDayStart(LocalDate time) {
        return LocalDateTime.of(time, LocalTime.MIN);
    }

    /**
     * 获取一天的结束时间
     * 示例:2020-08-21T23:59:59.999999999
     */
    public static LocalDateTime getDayEnd(LocalDate time) {
        return LocalDateTime.of(time, LocalTime.MAX);
    }


    /**
     * 获取当月的开始时间
     * 示例: 2020-08-01T00:00
     */
    public static LocalDateTime getMonthStart(LocalDate time) {
        return LocalDateTime.of(time.with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    /**
     * 获取当月的结束时间
     * 示例: 2020-08-31T23:59:59.999999999
     */
    public static LocalDateTime getMonthDayEnd(LocalDate time) {
        return LocalDateTime.of(time.with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MAX);
    }


    // =============================== 2、时间转字符串 =====================================

    /**
     * 获取 当前 日期时间字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return DateTimeFormatter.ofPattern(DATETIME_FORMATTER).format(LocalDateTime.now());
    }

    /**
     * 获取 当前 日期字符串(yyyy-MM-dd)
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return DateTimeFormatter.ofPattern(DATE_FORMATTER).format(LocalDateTime.now());
    }

    /**
     * 获取 当前 时间字符串(yyyy-MM)
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return DateTimeFormatter.ofPattern(DATE_FORM).format(LocalDateTime.now());
    }

    /**
     * 获取 指定 日期时间字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getCurrentDateTimeStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern(DATETIME_FORMATTER).format(localDateTime);
    }

    /**
     * 获取 指定 日期字符串(yyyy-MM-dd)
     *
     * @return
     */
    public static String getCurrentDateStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern(DATE_FORMATTER).format(localDateTime);
    }

    /**
     * 获取 指定 时间字符串(yyyy-MM)
     *
     * @return
     */
    public static String getCurrentTimeStr(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern(DATE_FORM).format(localDateTime);
    }


    /**
     * 获取 指定 日期字符串(yyyy-MM-dd)
     *
     * @return
     */
    public static String getCurrentDateStr(LocalDate localDate) {
        if (localDate == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern(DATE_FORMATTER).format(localDate);
    }


    // =============================== 3、字符串转时间 =====================================


    /**
     * 将时间字符串转为自定义时间格式的LocalDateTime
     * 字符串格式: yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime convertStringToLocalDateTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }

    /**
     * 字符串格式: yyyy-MM-dd
     */
    public static LocalDate convertStringToLocalDate(String time) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(DATE_FORMATTER));
    }


    /**
     * 将时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param time   需要转化的时间字符串
     * @param format 自定义的时间格式
     * @return
     */
    public static LocalDateTime convertStringToLocalDateTime(String time, String format) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(format));
    }


    // ============================== 4、求两个时间的时间差 ==============================

    /**
     * 获取两个日期的 天数 差
     */
    public static long betweenLessDay(LocalDateTime startTime, LocalDateTime endTime) {
        //时间相减
        Duration duration = Duration.between(startTime, endTime);
        //两个时间差的天数
        return duration.toDays();
    }

    /**
     * 获取两个日期的 小时 差
     */
    public static long betweenLessHour(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours();
    }


    /**
     * 获取两个日期的 分钟 差
     */
    public static long betweenLessMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }

    /**
     * 获取两个日期的 秒 差
     */
    public static long betweenLessMillis(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMillis();
    }


    // ================================ 5. long和LocalDateTime互转 ============================

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long convertLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}

