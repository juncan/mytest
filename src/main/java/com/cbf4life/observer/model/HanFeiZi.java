package com.cbf4life.observer.model;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName HanFeiZi
 * @Description: TODO
 * @create 2018-08-26 10:57
 */
public class HanFeiZi implements Observable {
    //定义个变长数组，存放所有的观察者
    private ArrayList<Observer> observerList = new ArrayList<Observer>();

    //增加观察者
    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    //删除观察者
    @Override
    public void deleteObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    //通知所有的观察者
    @Override
    public void notifyObservers(String context) {
        for (Observer observer : observerList) {
            observer.update(context);
        }
    }

    //韩非子开始吃饭了
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了。。。");
        //通知所有的观察者
        this.notifyObservers("韩非子在吃饭");
    }
    //韩非子开始娱乐了，古代人没啥娱乐，你能想到的就那么多
    public void haveFun() {
        System.out.println("韩非子：开始娱乐了");
        this.notifyObservers("韩非子在娱乐");
    }

}
