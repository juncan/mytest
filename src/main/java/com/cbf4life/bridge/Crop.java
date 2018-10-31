package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName Crop
 * @Description: 定义一个公司的抽象类
 * @create 2018-08-05 21:27
 */
public abstract class Crop {

    /**
     * 是公司就一个有生产，甭管是什么软件公司还是制造业公司
     * 那每个公司的生产的东西都不一样，所以由实现类来实现
     */
    protected abstract void produce();

    /**
     * 有产品了，那肯定要销售呀，不销售公司怎么生存
     */
    protected abstract void sell();

    /**
     * 公司是干什么的？赚钱的呀，不赚钱傻子才干
     */
    public void makeMoney() {
        //每个公司都是一样，先生产
        this.produce();
        //然后销售
        this.sell();
    }
}
