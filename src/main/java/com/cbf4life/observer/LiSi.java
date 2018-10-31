package com.cbf4life.observer;

/**
 * @author wujc
 * @ClassName LiSi
 * @Description: TODO
 * @create 2018-08-13 23:05
 */
public class LiSi implements ILiSi {
    //首先李斯是个观察者，一旦韩非子有活动，他就知道，他就要向老板汇报
    @Override
    public void update(String context) {
        System.out.println("李斯：观察到韩非子活动，开始向老板汇报...");
        this.reportToQiShiHuang(context);
        System.out.println("李斯：汇报完毕，秦老板赏给他两个萝卜吃吃。。。");
    }

    //汇报给秦始皇
    private void reportToQiShiHuang(String report) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--->" + report);
    }
}
