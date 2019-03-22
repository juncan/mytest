package com.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wujc
 * @ClassName CglibProxyhandler
 * @Description: TODO
 * @create 2019-02-27
 */
public class CglibProxyhandler implements MethodInterceptor {
    //维护目标对象
    private Object target;

    public Object getProxyInstance(final Object target) {
        this.target = target;
        //Enhancer类是CGLIB中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
        Enhancer enhancer = new Enhancer();
        //将代理的对象设置成父类
        enhancer.setSuperclass(this.target.getClass());
        //回调方法，设置拦截器
        enhancer.setCallback(this);
        //动态创建一个代理
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理先进行谈判。。。");
        //唱歌需要明星自己来唱
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("演出完代理去收钱。。");
        return result;
    }
}
