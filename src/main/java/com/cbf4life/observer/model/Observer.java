package com.cbf4life.observer.model;

/**
 * @author wujc
 * @ClassName Observer
 * @Description: 所有观察者
 * @create 2018-08-26 16:38
 */
public interface Observer {
    //一发现别人有动静，自己也要行动起来
    public void update(String context);
}
