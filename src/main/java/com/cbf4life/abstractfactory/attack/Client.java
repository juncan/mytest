package com.cbf4life.abstractfactory.attack;

/**
 * @author xingkong
 * @date 2023/3/24 13:59
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("游戏开始。。。。。");

        //第一位玩家选择人类族
        System.out.println("工作建造人类族工厂。。。。");
        AbstractFactory factory = new HumanFactory(10, 10);

        Unit marine = factory.createLowClass();
        marine.show();

        Unit tank = factory.createMidClass();
        tank.show();

        Unit ship = factory.createHighClass();
        ship.show();

        factory = new AlienFactory(200, 200);

        Unit roach = factory.createLowClass();
        roach.show();

        Unit poison = factory.createMidClass();
        poison.show();

        Unit mammoth = factory.createHighClass();
        mammoth.show();

        System.out.println("两族开始大混战");


        marine.attack();
        roach.attack();

        poison.attack();
        tank.attack();

        mammoth.attack();
        ship.attack();
    }
}
