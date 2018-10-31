package com.cbf4life.singleton2;

/**
 * @author wujc
 * @ClassName Minister
 * @Description: 大臣们悲惨了，一个皇帝都伺候不过来，现在还来两个皇帝
 * TND，不管了，找到个皇帝，磕头，请安就成了
 * @create 2018-07-15 15:36
 */
@SuppressWarnings("all")
public class Minister {
    public static void main(String[] args) {
        int minsterNum = 10; //10个大臣
        for (int i = 0; i < minsterNum; i++) {
            Emperor emperor = Emperor.getInstance();
            System.out.println("第" + (i + 1) + "个大臣参拜的是：");
            emperor.emperorInfo();
        }
    }
}
