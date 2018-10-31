package com.cbf4life.factory;

/**
 * @author wujc
 * @ClassName YelloHuman
 * @Description: TODO
 * @create 2018-07-15 15:53
 */
public class YelloHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黄色人类会大笑,幸福呀！");
    }

    @Override
    public void cry() {
        System.out.println("黄色人类会哭");
    }

    @Override
    public void talk() {
        System.out.println("黄色人类会说话，一般说的都是双字节");
    }
}
