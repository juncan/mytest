package com.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xingkong
 * @date 2022/6/11 20:00
 */
public class MyProxy implements InvocationHandler {
    //用于接收目标类的 参数
    private Object tar;

    public Object bind(Object target) {
        this.tar = target;
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //执行一些方法
        System.out.println("Do something before");

        //目标类的方法执行，这里实际上是执行目标对象的方法

        result = method.invoke(tar, args);

        System.out.println("Do something after");

        return result;
    }
}
