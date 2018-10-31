package com.cbf4life.singletonl;

/**
 * @author wujc
 * @ClassName Minster
 * @Description: 大臣是天天要面见皇帝，今天见的皇帝和昨天见的，前天不一样就出问题了
 * @create 2018-07-15 15:03
 */
@SuppressWarnings("all")
public class Minster {
    public static void main(String[] args) {
        //第一天
        Emperor emperor1 = Emperor.getInstance();
        emperor1.emperorInfo();

        //第二天
        Emperor emperor2 = Emperor.getInstance();
        emperor2.emperorInfo();

        //第三天
        Emperor emperor3 = Emperor.getInstance();
        emperor3.emperorInfo();

        //三天见的皇帝都是同一个人，荣幸吧
    }
}
