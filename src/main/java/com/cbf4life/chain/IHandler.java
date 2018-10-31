package com.cbf4life.chain;

/**
 * @author wujc
 * @ClassName IHandler
 * @Description: TODO
 * @create 2018-08-26 21:23
 */
public interface IHandler {
    //一个女性（女儿，妻子或者母亲）要求逛街，你要处理这个请求
    public void HandleMessage(IWomen women);
}
