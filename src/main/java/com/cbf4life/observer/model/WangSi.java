package com.cbf4life.observer.model;

/**
 * @author wujc
 * @ClassName WangSi
 * @Description: TODO
 * @create 2018-08-26 16:51
 */
public class WangSi implements Observer {
    //王斯，看到韩非子有活动，自己就 受不了
    @Override
    public void update(String context) {
        System.out.println("王斯：观察到韩非子活动，自己也开始活动。。。");
        this.cry(context);
        System.out.println("王斯：真真的哭死了。。。");

    }

    //一看李斯有活动，就哭，痛苦
    private void cry(String context) {
        System.out.println("王斯：因为" + context + ",所有我悲伤啊");
    }
}
