package com.cbf4life.observer.model;

/**
 * @author wujc
 * @ClassName LiuSi
 * @Description: TODO
 * @create 2018-08-26 17:01
 */
public class LiuSi implements Observer {
    //刘斯，观察到韩非子的活动，自己也做一定的事情
    @Override
    public void update(String context) {
        System.out.println("刘斯：观察到韩非子活动，开始动作了");
        this.happy(context);
        System.out.println("刘斯，真被乐死了...");

    }

    //一看到韩非子有变化，他就快乐
    private void happy(String context) {
        System.out.println("刘斯：因为：" + context+",所以很快乐");
    }
}
