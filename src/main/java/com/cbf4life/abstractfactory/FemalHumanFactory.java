package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName FemalHumanFactory
 * @Description: 男性创建工厂
 * @create 2018-07-15 21:38
 */
public class FemalHumanFactory extends AbstractHumanFactory{
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowFemaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhileFemaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackFemaleHuman);
    }

}
