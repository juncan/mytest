package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName IPodCorp
 * @Description: 我是山赛老大，你流行啥我就生产啥
 * @create 2018-08-05 21:53
 */
public class IPodCorp extends Crop{
    //我开始生产ipod
    @Override
    protected void produce() {
        System.out.println("我生产ipod...");
    }
    //山赛的ipod很畅销，便宜啊
    @Override
    protected void sell() {
        System.out.println("ipod畅销。。。");
    }

    //狂赚钱
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱了。。。。");
    }
}
