package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName IPod
 * @Description: 生产ipod了
 * @create 2018-08-05 22:07
 */
public class IPod extends Product{
    @Override
    public void beProducted() {
        System.out.println("生产出的ipod是这个样子的。。。");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的ipod卖出去了。。。");
    }
}
