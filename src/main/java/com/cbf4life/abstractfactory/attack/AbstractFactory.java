package com.cbf4life.abstractfactory.attack;

/**
 * 抽象兵工厂接口
 *
 * @author xingkong
 * @date 2023/3/24 13:49
 */
public interface AbstractFactory {
    //初级兵种制造标准
    LowClassUnit createLowClass();

    //中级兵种制造标准
    MidClassUnit createMidClass();

    //高级兵种制造标准
    HighClassUnit createHighClass();
}
