package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName HouseCrop
 * @Description: 房地产公司，按照翻译来说应该叫realty crop,这个是比较准确的翻译
 * 但是我问你房地产公司翻译成英文，你的第一反应是什么？对嘛还是house crop!
 * @create 2018-08-05 21:37
 */
public class HouseCrop extends Crop {
    //房地产公司就是盖房子
    @Override
    protected void produce() {
        System.out.println("房地产公司盖房子。。。");
    }

    //房地产卖房子，自己住那可不赚钱
    @Override
    protected void sell() {
        System.out.println("房地产公司出售房子。。。");
    }

    //房地产公司很High了，赚钱，计算利润
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚钱了。。");
    }
}
