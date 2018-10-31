package com.cbf4life.bridge;

/**
 * @author wujc
 * @ClassName Product
 * @Description: 这个是我整个集团公司的产品类
 * @create 2018-08-05 22:00
 */
public abstract class Product {
    //甭管是什么产品它总是能被生产出来
    public abstract void beProducted();

    //生产出来的东西，一定要销售出去，，否则亏本了
    public abstract void beSelled();
}
