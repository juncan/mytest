package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName HumanEnum
 * @Description: 世界上有哪些类型的人，列出来
 * @create 2018-07-15 20:43
 */
public enum HumanEnum {
    //把世界上所有人类都定义出来
    YellowMaleHuman("com.cbf4life.abstractfactory.YellowMaleHuman"),
    YellowFemaleHuman("com.cbf4life.abstractfactory.YellowFemaleHuman"),
    WhileMaleHuman("com.cbf4life.abstractfactory.WhileMaleHuman"),
    WhileFemaleHuman("com.cbf4life.abstractfactory.WhileFemaleHuman"),
    BlackMaleHuman("com.cbf4life.abstractfactory.BlackMaleHuman"),
    BlackFemaleHuman("com.cbf4life.abstractfactory.BlackFemaleHuman");
    private String value = "";

    //定义构造函数，目的是Data(value)类型的相匹配的
    private HumanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
