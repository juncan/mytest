package com.cbf4life.abstractfactory.attack;

/**
 * 海军陆战队员
 * @author xingkong
 * @date 2023/3/24 11:48
 */
public class Marine extends LowClassUnit{
    public Marine(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("士兵出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("士兵用机枪射击，攻击力：" + attack);
    }
}
