package com.cbf4life.abstractfactory;

/**
 * @author wujc
 * @ClassName AbstractBlackHuman
 * @Description: 黑色人类
 * @create 2018-07-15 20:03
 */
public abstract class AbstractBlackHuman implements Human {
    @Override
    public void cry() {
        System.out.println("白色人类会哭");
    }

    @Override
    public void laugh() {
        System.out.println("黑人会笑");
    }

    @Override
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
