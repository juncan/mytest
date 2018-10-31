package com.cbf4life.factory;

/**
 * @author wujc
 * @ClassName BlackHuman
 * @Description: 黑色人类，记得中学学英语，老师说black man是侮辱人的意思，没跟老外说话
 * @create 2018-07-15 15:58
 */
public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑人会笑");
    }

    @Override
    public void cry() {
        System.out.println("黑人会哭");
    }

    @Override
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
