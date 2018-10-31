package com.cbf4life.observer.model;


/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-08-26 17:08
 */
public class Client {
    public static void main(String[] args) {
        //三个观察者产生出来
        Observer lisi = new LiSi();
        Observer wangsi = new WangSi();
        Observer liusi = new LiuSi();

        //定义出韩非子
        HanFeiZi hanFeizi = new HanFeiZi();

        //我们后人根据历史，描述这个场景，有三个人在观察韩非子
        hanFeizi.addObserver(lisi);
        hanFeizi.addObserver(wangsi);
        hanFeizi.addObserver(lisi);

        //然后看看韩非子在干吗
        hanFeizi.haveBreakfast();

    }
}
