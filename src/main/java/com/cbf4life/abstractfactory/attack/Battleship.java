package com.cbf4life.abstractfactory.attack;

/**
 * 巨型战舰类
 * @author xingkong
 * @date 2023/3/24 13:42
 */
public class Battleship extends HighClassUnit{
    public Battleship(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("战舰出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("战舰用激光炮打击，攻击力：" + attack);
    }
}
