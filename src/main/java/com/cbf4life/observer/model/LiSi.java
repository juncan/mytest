package com.cbf4life.observer.model;

/**
 * @author wujc
 * @ClassName LiSi
 * @Description: TODO
 * @create 2018-08-26 16:41
 */
public class LiSi implements Observer {
    @Override
    public void update(String str) {
        System.out.println("李斯：观察到李斯活动，开始向老板报告");
        this.reportToQiShiHuang(str);
        System.out.println("李斯：汇报完毕，秦老板赏给他两个萝卜吃。。。");

    }

    private void reportToQiShiHuang(String str) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了---" + str);
    }
}
