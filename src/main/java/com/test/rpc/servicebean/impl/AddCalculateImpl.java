package com.test.rpc.servicebean.impl;

import com.test.rpc.servicebean.AddCalculate;

/**
 * @author wujc
 * @ClassName AddCalculateImpl
 * @create 2019-12-20
 */
public class AddCalculateImpl implements AddCalculate {
    //两数相加
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
