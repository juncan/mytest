package com.test.proxy;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2019-02-27
 */
public class Client {
    public static void main(String[] args) {
        Star realStar = new RealStar();
        //创建一个代理对象实例
        Star proxy = (Star) new JdkProxyHander(realStar).getProxyInstance();

        proxy.sing();
    }
}
