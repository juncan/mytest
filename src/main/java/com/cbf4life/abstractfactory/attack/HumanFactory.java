package com.cbf4life.abstractfactory.attack;

/**
 * 人类兵工厂
 *
 * @author xingkong
 * @date 2023/3/24 13:52
 */
public class HumanFactory implements AbstractFactory {
    //工厂横坐标
    private int x;

    //工厂纵坐标
    private int y;

    public HumanFactory(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public LowClassUnit createLowClass() {
        LowClassUnit unit = new Marine(x, y);
        System.out.println("制造海军陆战员成功");
        return unit;
    }

    @Override
    public MidClassUnit createMidClass() {
        MidClassUnit unit = new Tank(x, y);
        System.out.println("制造变形坦克成功");
        return unit;
    }

    @Override
    public HighClassUnit createHighClass() {
        HighClassUnit unit = new Battleship(x, y);
        System.out.println("制造巨型战舰成功");
        return unit;
    }
}
