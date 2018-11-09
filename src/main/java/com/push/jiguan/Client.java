package com.push.jiguan;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-08 13:55
 */
public class Client {
    public static void main(String[] args) {
        JpushDto jDto = new JpushDto();
        jDto.setAlert("极光推送");
        Set<String> alias = new HashSet<String>();
        alias.add("12344");
        jDto.setAlias(alias);
        jDto.setTitle("测试");
        jDto.setJpushAudienceType(Constant.JpushAudienceType.ALL);
        JgPush jgPush = new JgPush();
        jgPush.handlerJpush(jDto);

    }
}
