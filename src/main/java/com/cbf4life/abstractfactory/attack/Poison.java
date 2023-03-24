package com.cbf4life.abstractfactory.attack;

/**
 * 毒液类
 * @author xingkong
 * @date 2023/3/24 13:45
 */
public class Poison extends MidClassUnit{
    public Poison(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("毒液兵出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("毒液兵用毒液喷射，攻击力：" + attack);
    }
}
