package com.test.weixin.model;

/**
 * 描述:
 *模板详细信息
 * @author administrator
 * @create 2018-06-28 22:34
 */
public class TemplateData {
    private String value;
    private String color;

    public TemplateData(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
