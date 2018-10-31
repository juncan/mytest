package com.cbf4life.chain;

import com.cbf4life.observer.IHanFeiZi;

/**
 * @author wujc
 * @ClassName Father
 * @Description: TODO
 * @create 2018-08-26 21:29
 */
public class Father implements IHandler {
    //未出嫁女儿请示父亲
    @Override
    public void HandleMessage(IWomen women) {
        System.out.println("女儿的请示是：" + women.getRequest());
        System.out.println("父亲的答复是：同意");
    }
}
