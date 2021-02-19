package com.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Classname CommonUtils
 * @Description TODO
 * @Date 2020/10/19 17:12
 * @Created by wujc
 */
public class CommonUtils {
    // 手机号码前三后四脱敏
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
