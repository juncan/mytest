package com.cbf4life.template;

/**
 * @author wujc
 * @ClassName BenzModel
 * @Description: 奔驰车辆模型
 * @create 2018-07-18 22:25
 */
public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰车跑起来是这个样子的。。。");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰车应该这样停车。。。");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这样的。。。");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这个声音的。。。");
    }
}
