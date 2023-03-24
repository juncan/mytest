package com.cbf4life.abstractfactory.attack;

/**
 * 兵种抽象类
 *
 * @author xingkong
 * @date 2023/3/24 11:42
 */
public abstract class Unit {
    //攻击力
    protected int attack;
    //防御力
    protected int defence;
    //生命力
    protected int health;
    //横坐标
    protected int x;
    //纵坐标
    protected int y;

    public Unit(int attack, int defence, int health, int x, int y) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.x = x;
        this.y = y;
    }

    public abstract void show();

    public abstract void attack();
}
