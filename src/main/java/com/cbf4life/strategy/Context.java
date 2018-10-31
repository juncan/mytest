package com.cbf4life.strategy;

/**
 * @author wujc
 * @ClassName Context
 * @Description: 计谋有了，那还要有锦囊
 * @create 2018-07-15 10:37
 */
public class Context {
    //构造函数，你要使用哪个妙计
    private Istrategy istrategy;

    public Context(Istrategy istrategy) {
        this.istrategy = istrategy;
    }

    //使用计谋了，看我出招了
    public void operate() {
        this.istrategy.operate();
    }
}
