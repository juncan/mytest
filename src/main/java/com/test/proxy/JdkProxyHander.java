package com.test.proxy;

import java.lang.reflect.Proxy;

/**
 * @author wujc
 * @ClassName JdkProxyHander
 * @Description: TODO
 * @create 2019-02-27
 */
public class JdkProxyHander {

    //用来接收真实明星对象
    private Object realStar;

    public JdkProxyHander(Object realStar) {
        super();
        this.realStar = realStar;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(realStar.getClass().getClassLoader(),
                realStar.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("代理先进行谈判");
                    //唱歌明星自己来唱
                    Object object = method.invoke(realStar, args);
                    System.out.println("演出完代理去收钱");
                    return object;
                });
    }
}
