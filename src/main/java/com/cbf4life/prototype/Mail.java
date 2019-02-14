package com.cbf4life.prototype;

/**
 * @author wujc
 * @ClassName Mail
 * @Description: TODO
 * @create 2018-11-10 15:18
 */
public class Mail implements Cloneable {
    //收件人
    private String receiver;

    //邮件名称
    private String subject;

    //称谓
    private String appllation;

    //邮件内容
    private String context;


    //邮件的尾部，一般都是加上“XXX版权所有”等信息
    private String tail;

    public Mail(AdvTemplate advTemplate) {
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvSubject();
    }

    @Override
    protected Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAppllation() {
        return appllation;
    }

    public void setAppllation(String appllation) {
        this.appllation = appllation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }
}
