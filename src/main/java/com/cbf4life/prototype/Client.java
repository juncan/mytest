package com.cbf4life.prototype;

import java.util.Random;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-10 15:21
 */
public class Client {
    //发送账单的数量，这个值是从数据库中获得
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {
        //模拟发送邮件
        int i = 0;
        //把模板定义出来，这个是从数据库中获得
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行版权所有");

        while (i < MAX_COUNT) {
            //以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();

            cloneMail.setAppllation(getRandString(5) + " 先生（女士）");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");

            //然后发送邮件
            senMail(cloneMail);
            i++;
        }
    }

    //获取指定长度的随机字符串
    private static String getRandString(int maxLength) {
        String source = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < maxLength; i++) {
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();
    }

    //发送邮件
    private static void senMail(Mail mail) {
        System.out.println("标题：" + mail.getSubject() + "\t收件人" + mail.getReceiver()
                + "\t......发送成功");
    }
}
