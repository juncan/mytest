package com.cbf4life.factory;

/**
 * @author wujc
 * @ClassName WhiteHuman
 * @Description: 白色人类
 * @create 2018-07-15 15:55
 */
public class WhiteHuman implements Human{
    @Override
    public void laugh() {
        System.out.println("白色人类会大笑，侵略的笑声");
    }

    @Override
    public void cry() {
        System.out.println("白色人类会哭");
    }

    @Override
    public void talk() {
        System.out.println("白色人类会说话，一般都是单字节");
    }
}
