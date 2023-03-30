package com.cbf4life.adapter.pin;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/3/29 17:59
 */
public interface TriplePin {
    //参数分别为火线、零线、地线
    public void electrify(int l, int n, int e);
}
