package com.cbf4life.template;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName BenzBuilder
 * @Description: 各种设施都给了，我们按照一定的顺序制造一个奔驰车
 * @create 2018-07-18 22:46
 */
public class BenzBuilder extends CarBuilder{
    private BenzModel benz = new BenzModel();
    @Override
    public void setSequeue(ArrayList<String> sequeue) {
        this.benz.setSequence(sequeue);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
