package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName ClothesCrop
 * @Description: 服装公司，这个行业现现在不怎么样
 * @create 2018-08-05 21:44
 */
public class ClothesCrop extends Crop{
    //服装公司生产的就是衣服
    @Override
    protected void produce() {
        System.out.println("服装公司生产衣服");
    }

    //服装公司卖服装，可只卖服装，不买穿衣服的模特
    @Override
    protected void sell() {
        System.out.println("服装公司出售衣服。。。");
    }

    //服装公司不景气，但是怎么说也是赚钱行业
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("服装公司赚小钱。。。");
    }
}
