package com.cbf4life.template;

/**
 * @author wujc
 * @ClassName HummerH2Model
 * @Description: H1和H2有什么差别，还真不知道，真没接触过悍马
 * @create 2018-07-18 21:32
 */
public class HummerH2Model extends HummerModel{
    @Override
    public void start() {
        System.out.println("悍马H2发动。。。");
    }

    @Override
    public void stop() {
        System.out.println("悍马H2停车。。。");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛。。。");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎声音是这样。。。");
    }

}
