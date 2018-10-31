package com.cbf4life.template;

/**
 * @author wujc
 * @ClassName BMWModel
 * @Description: 宝马车模型
 * @create 2018-07-18 22:31
 */
public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马车跑起来是这个样子的。。。");
    }

    @Override
    protected void stop() {
        System.out.println("宝马车应该是这样停车。。。");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马车的喇叭声音是这样的。。。");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马车的引擎是这个声音的。。。");
    }
}
