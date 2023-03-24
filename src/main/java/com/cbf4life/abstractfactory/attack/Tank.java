package com.cbf4life.abstractfactory.attack;

/**
 * 变形坦克类
 * @author xingkong
 * @date 2023/3/24 13:40
 */
public class Tank extends MidClassUnit{
    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("坦克出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("坦克用炮轰击，攻击类：" + attack);
    }
}
