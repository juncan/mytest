package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName AbstractWhiteHuman
 * @Description: 白色人人类
 * @create 2018-07-15 20:01
 */
public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void cry() {
        System.out.println("白色人类会哭");
    }

    @Override
    public void laugh() {
        System.out.println("白色人类会大笑，侵略的笑");
    }

    @Override
    public void talk() {
        System.out.println("白色人类会说话，一般说的都是单字节");
    }
}
