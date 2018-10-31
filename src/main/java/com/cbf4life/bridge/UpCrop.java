package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName UpCrop
 * @Description: 升级的公司抽象类
 * @create 2018-08-05 22:09
 */
public abstract class UpCrop {
    //定义一个产品对象，抽象的了，不知道具体是什么产品
    private Product product;

    //构造函数，由子类定义传递具体的产品进来
    public UpCrop(Product product) {
        this.product = product;
    }

    //公司是干什么的？赚钱的呀，不赚钱傻子才干
    public void makeMoney() {
        //每个公司都是一样，先生产
        this.product.beProducted();

        //然后销售
        this.product.beSelled();
    }
}
