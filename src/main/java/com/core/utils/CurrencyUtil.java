package com.core.utils;

import cn.hutool.core.convert.Convert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xingkong
 * @Title: 金额计算工具
 * @Description: CurrencyUtil
 * @date 2022/1/18 10:23
 */
public final class CurrencyUtil {

    /**
     * 默认除法运算精度
     */
    public static final int DEF_DIV_SCALE = 2;

    /**
     * 提供精确的累加运算
     *
     * @param params 累加值
     * @return 累加之和
     */
    public static BigDecimal add(BigDecimal... params) {
        BigDecimal result = new BigDecimal("0");
        for (BigDecimal param : params) {
            result = result.add(param).setScale(2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * 提供精确的减法运算
     *
     * @param params 参数
     * @return 减法所得到的值
     */
    public static BigDecimal sub(BigDecimal... params) {
        BigDecimal result = params[0];
        List<BigDecimal> decimalList = Arrays.stream(params).skip(1).collect(Collectors.toList());
        for (BigDecimal param : decimalList) {
            result = result.subtract(param).setScale(2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     *提供精确的乘法运算
     * @param v1 被乘数
     * @param v2 乘数
     * @param scale 表示需要精确到小数点后几位
     * @return 乘积值
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.multiply(v2).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     *提供精确的乘法运算
     * @param v1 被乘数
     * @param v2 乘数
     * @return 乘积值
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, DEF_DIV_SCALE);
    }


    /**
     * 提供精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供精确的除法运算，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 精度
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        //如果除数等于0，则返回0
        if (v2.compareTo(BigDecimal.ZERO)==0) {
            return BigDecimal.ZERO;
        }
        return v1.divide(v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 金额 转分
     * @param money 金额
     * @return 转换成单位分
     */
    public static Integer fen(BigDecimal money) {
        BigDecimal price = mul(money, new BigDecimal("100"));

        return Convert.toInt(price);
    }

    /**
     * 分转金额
     * @param money 金额
     * @return 金额
     */
    public static BigDecimal reversalFen(BigDecimal money) {
        return div(money, new BigDecimal("100"));
    }

}
