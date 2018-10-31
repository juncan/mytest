package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName ShanZhaiCrop
 * @Description: 我是山赛老大，你流行啥我就生产啥
 * @create 2018-08-05 22:15
 */
public class ShanZhaiCrop extends UpCrop{
    //产什么产品，不知道，等被调用的才知道
    public ShanZhaiCrop(Product product) {
        super(product);
    }

    //狂赚钱

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱呀。。。");
    }
}
