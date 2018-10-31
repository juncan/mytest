package com.cbf4life.template;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName CarBuilder
 * @Description: 要什么顺序的车，你说，我给你制造
 * @create 2018-07-18 22:43
 */
public abstract class CarBuilder {
    //建造一个模型，你要给我一个顺序，就组装顺序
    public abstract void setSequeue(ArrayList<String> sequeue);

    //设置完毕顺序后，就可以直接拿到这个车辆模型
    public abstract CarModel getCarModel();
}
