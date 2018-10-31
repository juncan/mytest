package com.test;

/**
 * 描述:
 * RealSubject
 *
 * @author wujc
 * @create 2018-07-03 17:30
 */
public class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("From real Subject");
    }
}
