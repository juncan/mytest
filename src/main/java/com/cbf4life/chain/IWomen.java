package com.cbf4life.chain;

/**
 * @author wujc
 * @ClassName IWomen
 * @Description: 古代悲哀女性的总称
 * @create 2018-08-26 20:42
 */
public interface IWomen {
    //获得个人状况
    public int getType();

    //获得个人请示，你要干什么，出去逛街、约会、看电影
    public String getRequest();
}
