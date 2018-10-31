package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName HumanFactory
 * @Description: 这次定一个接口，应该要造不同性别的人，需要不同的生产线
 * 那这个八卦炉必须可以制造男人和女人
 * @create 2018-07-15 21:22
 */
public interface HumanFactory {
    //制造黄色人类
    public Human createYellowHuman();

    //制造一个白色人类
    public Human createWhiteHuman();

    //制造一个黑色人类
    public Human createBlackHuman();
}
