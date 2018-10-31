package com.cbf4life.chain.change;

import com.cbf4life.chain.Handler;
import com.cbf4life.chain.IWomen;

/**
 * @author wujc
 * @ClassName Husband
 * @Description: 丈夫类
 * @create 2018-08-26 22:09
 */
public class Husband extends Handler {
    //丈夫只处理妻子的请求
    public Husband() {
        super(2);
    }
    @Override
    public void response(IWomen women) {
        System.out.println("-----------妻子向丈夫请示---------");
        System.out.println(women.getRequest());
        System.out.println("丈夫的答复是：同意\n");

    }
}
