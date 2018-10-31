package com.test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 * Client
 *
 * @author administrator
 * @create 2018-07-03 17:35
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        InvocationHandler ds = new DynamicSubject(subject);
        Class<?> cls = subject.getClass();

        Subject subject1 = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);
        System.out.println(subject1 instanceof Proxy);

        System.out.println("subject1 的class类是：" + subject1.getClass().toString());

        System.out.println("subject1 的属性有：");
        Field[] fields = subject1.getClass().getFields();
        for (Field field: fields) {
            System.out.println(field.getName() + ",");
        }

        System.out.println("\n"+"subject1的方法有：");
        Method[] methods = subject1.getClass().getMethods();
        for (Method method: methods) {
            System.out.println(method.getName() +",");
        }

        System.out.println("\n" + "subject1的父类是：" + subject1.getClass().getSuperclass());

        System.out.println("\n" + "subject1实现的接口是：");

        Class<?>[] interfaces = subject1.getClass().getInterfaces();
        for (Class<?> cl: interfaces) {
            System.out.println(cl.getName() + ",");
        }
        System.out.println("运算结果为：" );
        subject1.request();
    }
}
