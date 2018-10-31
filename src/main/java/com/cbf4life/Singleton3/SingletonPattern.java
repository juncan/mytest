package com.cbf4life.Singleton3;

/**
 * @author wujc
 * @ClassName SingletonPattern
 * @Description: 通用单例模式
 * @create 2018-07-15 15:08
 */
@SuppressWarnings("all")
public class SingletonPattern {
    private static SingletonPattern singletonPattern = new SingletonPattern();

    //限制住不能直接产生一个实例
    private SingletonPattern() {

    }

    private SingletonPattern getInstance() {
        return singletonPattern;
    }
}
