package com.push.jiguan;

import java.util.Map;
import java.util.Set;

import com.push.jiguan.Constant.JpushAudienceType;

/**
 * @author wujc
 * @ClassName JpushDto
 * @Description: TODO
 * @create 2018-11-08 11:48
 */
public class JpushDto {
    private Set<String> alias;//别名
    private String title; //标题
    private String alert; //通知内容
    private Map<String,String> extras; //附加数据
    private JpushAudienceType jpushAudienceType;

    public Set<String> getAlias() {
        return alias;
    }

    public JpushDto setAlias(Set<String> alias) {
        this.alias = alias;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JpushDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAlert() {
        return alert;
    }

    public JpushDto setAlert(String alert) {
        this.alert = alert;
        return this;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public JpushDto setExtras(Map<String, String> extras) {
        this.extras = extras;
        return this;
    }

    public JpushAudienceType getJpushAudienceType() {
        return jpushAudienceType;
    }

    public JpushDto setJpushAudienceType(JpushAudienceType jpushAudienceType) {
        this.jpushAudienceType = jpushAudienceType;
        return this;
    }
}
