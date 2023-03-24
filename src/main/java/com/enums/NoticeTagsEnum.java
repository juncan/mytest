package com.enums;

import com.google.common.base.Enums;

/**
 * @author xingkong
 * @Title: NoticeTagsEnum
 * @Description: 提醒tags
 * @date 2021/12/22 11:53
 */
public enum NoticeTagsEnum {
    /**
     * 站内消息提醒
     */
    MESSAGE("消息提醒"),
    /**
     * 短信消息提醒
     */
    SMS("短信消息提醒"),

    TEMPLATEMSG("模板消息"),

    DRAW("中奖");


    private final String description;

    NoticeTagsEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public static NoticeTagsEnum getIfPresent(String name) {
        return Enums.getIfPresent(NoticeTagsEnum.class, name).orNull();
    }

}
