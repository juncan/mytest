package com.cbf4life.observer.change;

/**
 * @author wujc
 * @ClassName Client
 * @Description: 这个Client就是我们，用我们的视觉看待这段历史
 * @create 2018-08-26 10:44
 */
public class Client {
    public static void main(String[] args) {
        //定义出韩非子
        HanFeizi hanFeizi = new HanFeizi();

        //然后这里我们看看韩非子在干什么
        hanFeizi.haveBreakfast();

        //韩非子娱乐了
        hanFeizi.haveBreakfast();

    }
}
