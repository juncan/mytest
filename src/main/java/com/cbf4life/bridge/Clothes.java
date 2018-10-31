package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName Clothes
 * @Description: 我集团生产的衣服
 * @create 2018-08-05 22:05
 */
public class Clothes extends Product{
    @Override
    public void beProducted() {
        System.out.println("生产出的衣服是这个样子的。。。");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的衣服卖出去了。。。");
    }
}
