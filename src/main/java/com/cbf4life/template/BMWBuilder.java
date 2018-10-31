package com.cbf4life.template;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName BMWBuilder
 * @Description: 给定一个顺序，返回一个宝马车
 * @create 2018-07-18 22:51
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();
    @Override
    public void setSequeue(ArrayList<String> sequeue) {
        this.bmw.setSequence(sequeue);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}
