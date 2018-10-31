package com.cbf4life.chain.change;

import com.cbf4life.chain.Handler;
import com.cbf4life.chain.IWomen;

/**
 * @author wujc
 * @ClassName Son
 * @Description: TODO
 * @create 2018-08-26 22:14
 */
public class Son extends Handler {
    //儿子只处理母亲的请求
    public Son() {
        super(3);
    }
    //儿子的答复
    @Override
    public void response(IWomen women) {
        System.out.println("--------母亲向儿子请示-------");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意");

    }
}
