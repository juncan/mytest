package com.cbf4life.abstractfactory.attack;

/**
 * 螳螂类
 * @author xingkong
 * @date 2023/3/24 13:43
 */
public class Roach extends LowClassUnit{

    public Roach(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("螳螂兵出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("螳螂兵用爪子挠，攻击力：" + attack);
    }
}
