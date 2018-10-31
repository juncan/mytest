package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName NvWa
 * @Description: 女娲建立起两条生成线，分别是：
 * 男性生产线
 * 女性生产线
 * @create 2018-07-15 21:40
 */
public class NvWa {
    public static void main(String[] args) {
        //第一条生产线，男性生产线
        HumanFactory maleHumanFactory = new MaleHumanFactory();

        //第二天生产线，女性生产线
        HumanFactory femalHumanFactory = new FemalHumanFactory();

        //生产线建立完毕，开始生产人
        Human maleYellowHuman = maleHumanFactory.createYellowHuman();

        Human femaleYellowHuman = femalHumanFactory.createYellowHuman();

        maleYellowHuman.sex();
        maleYellowHuman.cry();
        maleYellowHuman.laugh();
        maleYellowHuman.talk();

        femaleYellowHuman.sex();
        femaleYellowHuman.cry();
        femaleYellowHuman.laugh();
        femaleYellowHuman.talk();

    }
}
