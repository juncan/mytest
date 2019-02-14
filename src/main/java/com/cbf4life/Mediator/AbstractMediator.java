package com.cbf4life.Mediator;

/**
 * @author wujc
 * @ClassName AbstractMediator
 * @Description: TODO
 * @create 2018-11-10 16:47
 */
public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    //构造函数
    public AbstractMediator() {
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    //中介者最重要的方法，叫做事情方法，处理多个对象之间的关系
    public abstract void execute(String str, Object... objects);
}
