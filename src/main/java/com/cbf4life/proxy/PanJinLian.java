package com.cbf4life.proxy;

/**
 * @author wujc
 * @ClassName PanJinLian
 * @Description: 定一个潘金莲是什么样的人
 * @create 2018-07-15 10:59
 */
public class PanJinLian  implements KindWomen{
    @Override
    public void makeEyesWithMan() {
        System.out.println("潘金莲抛媚眼");
    }

    @Override
    public void happyWithMan() {
        System.out.println("潘金莲在和那个男人做那个。。。");
    }
}
