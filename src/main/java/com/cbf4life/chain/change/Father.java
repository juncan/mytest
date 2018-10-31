package com.cbf4life.chain.change;

import com.cbf4life.chain.Handler;
import com.cbf4life.chain.IWomen;

/**
 * @author wujc
 * @ClassName Father
 * @Description: TODO
 * @create 2018-08-26 22:06
 */
public class Father extends Handler {
    //父亲只处理女儿的请求
    public Father() {
        super(1);
    }
    @Override
    public void response(IWomen women) {
        System.out.println("---------女儿向父亲请示------");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是：同意");

    }
}
