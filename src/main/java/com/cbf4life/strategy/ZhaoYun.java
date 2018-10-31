package com.cbf4life.strategy;

/**
 * @author wujc
 * @ClassName ZhaoYun
 * @Description: TODO
 * @create 2018-07-15 10:40
 */
public class ZhaoYun {

    /**
     * 赵云出场了，他根据诸葛亮给他的交代，依次拆开妙计
     * @param args
     */
    public static void main(String[] args) {
        Context context;
        //刚刚到吴国的时候拆开第一个
        System.out.println("------------------刚刚到吴国的时候拆开第一个-----------------");
        context = new Context(new BackDoor()); //拿到妙计
        context.operate(); //拆开执行
        System.out.println("\n\n");

        //刘备乐不思蜀，拆开第二个
        System.out.println("----------------------刘备乐不思蜀，拆开地问个-----------");
        context = new Context(new GivenGreenLight());
        context.operate(); //拆开第二个锦囊了
        System.out.println("\n\n");

        //孙权的小兵追来了，咋办？拆开第三个
        System.out.println("--------------孙权的小兵追来了，咋办？拆开第三个---------");
        context = new Context(new BlockEnemy());
        context.operate();
        System.out.println("\n\n");

        /**
         * 问题来了：赵云实际不知道是哪个策略啊，他只知道拆开第一个锦囊
         * 二不知道是BackDoor这个妙计，咋办？似乎这个策略模式已经把计谋名称写出来了
         *
         * 错！BackDoor、GivenGreenLight、BlockEnemy只是一个代码，你写成first、second、third，没人会说你错！
         * 策略模式的好处就是：体现了高内聚低耦合的特性啊，缺点嘛。。。。。
         */

    }
}
