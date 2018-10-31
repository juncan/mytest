package com.cbf4life.observer;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-08-13 23:17
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        //定义出韩非子和李斯
        LiSi lisi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();

        //观察早餐
        Watch watchBreakfast = new Watch(hanFeiZi, lisi, "breakfast");
        watchBreakfast.start();

        //观察娱乐情况
        Watch watchFun = new Watch(hanFeiZi, lisi, "fun");
        watchFun.start();

        //然后这里我们看看韩非子在干什么
        Thread.sleep(1000);
        hanFeiZi.haveBreakfast();

        //韩非子娱乐了
        Thread.sleep(1000);
        hanFeiZi.haveFun();

    }
}
