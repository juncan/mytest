package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName UpHouseCrop
 * @Description: TODO
 * @create 2018-08-05 22:13
 */
public class UpHouseCrop extends UpCrop{
    //定义传递一个House产品进来
    public UpHouseCrop(House house) {
        super(house);
    }

    //房地产公司很High了，赚钱，计算利润
    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚钱了。。。");
    }
}
