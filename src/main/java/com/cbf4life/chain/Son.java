package com.cbf4life.chain;

/**
 * @author wujc
 * @ClassName Son
 * @Description: TODO
 * @create 2018-08-26 21:33
 */
public class Son implements IHandler {
    @Override
    public void HandleMessage(IWomen women) {
        System.out.println("母亲的请示是：" + women.getRequest());
        System.out.println("儿子的答复是：同意");
    }
}
