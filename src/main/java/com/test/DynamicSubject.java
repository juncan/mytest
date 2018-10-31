package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述:
 * DynamicSubject
 *
 * @author wujc
 * @create 2018-07-03 17:31
 */
public class DynamicSubject implements InvocationHandler {
    private Object object;
    public DynamicSubject() {

    }

    public DynamicSubject(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method);
        method.invoke(object, args);
        System.out.println("after calling " + method);
        return null;
    }
}
