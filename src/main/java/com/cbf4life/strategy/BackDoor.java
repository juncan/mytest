package com.cbf4life.strategy;

/**
 * @author wujc
 * @ClassName BackDoor
 * @Description: 找乔国老帮忙，使孙权不能杀刘备
 * @create 2018-07-15 10:32
 */
public class BackDoor implements Istrategy {

    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }

}
