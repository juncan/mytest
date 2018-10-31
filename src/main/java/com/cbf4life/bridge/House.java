package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName House
 * @Description: 这是我集团公司盖的房子
 * @create 2018-08-05 22:02
 */
public class House extends Product{
    //豆腐渣就豆腐渣呗，好歹也是个房子
    @Override
    public void beProducted() {
        System.out.println("生产出的房子是这样的。。。");
    }

    //虽然是豆腐渣，也是能销售出去的
    @Override
    public void beSelled() {
        System.out.println("生产出的房子卖出去了。。。");
    }
}
