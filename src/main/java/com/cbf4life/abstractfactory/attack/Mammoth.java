package com.cbf4life.abstractfactory.attack;

/**
 * 猛犸类
 * @author xingkong
 * @date 2023/3/24 13:46
 */
public class Mammoth extends HighClassUnit{

    public Mammoth(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("猛犸巨兽出现在坐标：[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("猛犸巨兽用狼牙攻击，攻击力：" + attack);
    }
}
